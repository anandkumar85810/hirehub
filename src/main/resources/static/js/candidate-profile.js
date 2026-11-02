const profileForm = document.getElementById("profileForm");
const messageBox = document.getElementById("message");
const saveButton = document.getElementById("saveProfileBtn");

let profileExists = false;

document.addEventListener("DOMContentLoaded", function () {
    loadProfile();

    document.getElementById("logoutBtn").addEventListener("click", function () {
        localStorage.removeItem("token");
        localStorage.removeItem("user");
        window.location.href = "../login.html";
    });
});

async function loadProfile() {
    const token = localStorage.getItem("token");

    if (!token) {
        showMessage("Please login first.", "warning");
        return;
    }

    try {
        const response = await fetch("/api/candidate-profiles/me", {
            method: "GET",
            headers: {
                "Authorization": "Bearer " + token
            }
        });

        if (response.status === 404) {
            profileExists = false;
            saveButton.textContent = "Create Profile";
            return;
        }

        if (!response.ok) {
            throw new Error("Unable to load profile");
        }

        const profile = await response.json();
        profileExists = true;
        saveButton.textContent = "Update Profile";

        document.getElementById("profilePhoto").value =
            profile.profilePhoto || "";

        document.getElementById("education").value =
            profile.education || "";

        document.getElementById("experience").value =
            profile.experience ?? "";

        document.getElementById("skills").value =
            profile.skills || "";

        document.getElementById("expectedSalary").value =
            profile.expectedSalary ?? "";

        document.getElementById("location").value =
            profile.location || "";

        document.getElementById("bio").value =
            profile.bio || "";

    } catch (error) {
        console.error(error);
        showMessage("Could not load profile. Please check the API endpoint.", "danger");
    }
}

profileForm.addEventListener("submit", async function (event) {
    event.preventDefault();

    const token = localStorage.getItem("token");

    if (!token) {
        showMessage("Please login first.", "warning");
        return;
    }

    const profileData = {
        profilePhoto: document.getElementById("profilePhoto").value.trim(),
        education: document.getElementById("education").value.trim(),
        experience: Number(document.getElementById("experience").value),
        skills: document.getElementById("skills").value.trim(),
        expectedSalary: document.getElementById("expectedSalary").value
            ? Number(document.getElementById("expectedSalary").value)
            : null,
        location: document.getElementById("location").value.trim(),
        bio: document.getElementById("bio").value.trim()
    };

    const url = profileExists
        ? "/api/candidate-profiles/me"
        : "/api/candidate-profiles";

    const method = profileExists ? "PUT" : "POST";

    saveButton.disabled = true;
    saveButton.textContent = "Saving...";

    try {
        const response = await fetch(url, {
            method: method,
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + token
            },
            body: JSON.stringify(profileData)
        });

        const responseData = await response.json().catch(() => null);

        if (!response.ok) {
            const errorMessage =
                responseData?.message || "Failed to save profile";
            throw new Error(errorMessage);
        }

        profileExists = true;
        saveButton.textContent = "Update Profile";
        showMessage("Profile saved successfully!", "success");

    } catch (error) {
        console.error(error);
        showMessage(error.message || "Something went wrong.", "danger");
    } finally {
        saveButton.disabled = false;
        saveButton.textContent = profileExists ? "Update Profile" : "Create Profile";
    }
});

function showMessage(message, type) {
    messageBox.innerHTML = "";

    const alert = document.createElement("div");
    alert.className = `alert alert-${type}`;
    alert.textContent = message;

    messageBox.appendChild(alert);
}
