
const API_BASE = "http://localhost:8080/api";

const token = localStorage.getItem("token");
const params = new URLSearchParams(window.location.search);
const jobId = params.get("id");

const form = document.getElementById("editJobForm");
const messageBox = document.getElementById("messageBox");
const saveBtn = document.getElementById("saveBtn");

function showMessage(message, type = "danger") {
    messageBox.innerHTML = `
        <div class="alert alert-${type}" role="alert">
            ${message}
        </div>
    `;
}

function getHeaders() {
    return {
        "Content-Type": "application/json",
        "Authorization": `Bearer ${token}`
    };
}

// Load existing job details
async function loadJob() {
    if (!token) {
        window.location.href = "../login.html";
        return;
    }

    if (!jobId) {
        showMessage("Job ID URL mein nahi mila.");
        form.style.display = "none";
        return;
    }

    try {
        const response = await fetch(`${API_BASE}/jobs/${jobId}`, {
            method: "GET",
            headers: getHeaders()
        });

        if (!response.ok) {
            throw new Error("Job details load nahi ho paye.");
        }

        const job = await response.json();

        document.getElementById("title").value = job.title ?? "";
        document.getElementById("description").value = job.description ?? "";
        document.getElementById("location").value = job.location ?? "";
        document.getElementById("salaryMin").value = job.salaryMin ?? "";
        document.getElementById("salaryMax").value = job.salaryMax ?? "";
        document.getElementById("experienceRequired").value =
            job.experienceRequired ?? "";
        document.getElementById("employmentType").value =
            job.employmentType ?? "";
        document.getElementById("status").value = job.status ?? "ACTIVE";

    } catch (error) {
        showMessage(error.message);
    }
}

// Update job
form.addEventListener("submit", async function (event) {
    event.preventDefault();

    const salaryMinValue = document.getElementById("salaryMin").value;
    const salaryMaxValue = document.getElementById("salaryMax").value;
    const experienceValue =
        document.getElementById("experienceRequired").value;

    const salaryMin = salaryMinValue === "" ? null : Number(salaryMinValue);
    const salaryMax = salaryMaxValue === "" ? null : Number(salaryMaxValue);
    const experienceRequired =
        experienceValue === "" ? null : Number(experienceValue);

    if (salaryMin !== null && salaryMax !== null && salaryMin > salaryMax) {
        showMessage("Minimum salary maximum salary se zyada nahi ho sakti.");
        return;
    }

    const jobData = {
        title: document.getElementById("title").value.trim(),
        description: document.getElementById("description").value.trim(),
        location: document.getElementById("location").value.trim(),
        salaryMin,
        salaryMax,
        experienceRequired,
        employmentType: document.getElementById("employmentType").value,
        status: document.getElementById("status").value
    };

    saveBtn.disabled = true;
    saveBtn.textContent = "Saving...";

    try {
        const response = await fetch(`${API_BASE}/jobs/${jobId}`, {
            method: "PUT",
            headers: getHeaders(),
            body: JSON.stringify(jobData)
        });

        if (!response.ok) {
            const errorText = await response.text();
            throw new Error(errorText || "Job update nahi ho payi.");
        }

        showMessage("Job successfully updated!", "success");

        setTimeout(() => {
            window.location.href = "jobs.html";
        }, 1000);

    } catch (error) {
        showMessage(error.message);
    } finally {
        saveBtn.disabled = false;
        saveBtn.textContent = "Save Changes";
    }
});

// Logout
document.getElementById("logoutBtn").addEventListener("click", function (event) {
    event.preventDefault();

    localStorage.removeItem("token");
    localStorage.removeItem("user");

    window.location.href = "../login.html";
});

loadJob();
