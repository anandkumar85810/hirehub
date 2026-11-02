
const createJobForm = document.getElementById("createJobForm");
const messageBox = document.getElementById("message");

createJobForm.addEventListener("submit", async function (event) {
    event.preventDefault();

    const token = localStorage.getItem("token");

    if (!token) {
        messageBox.innerHTML =
            '<div class="alert alert-warning">Please login first.</div>';
        return;
    }

    const salaryMinValue = document.getElementById("salaryMin").value;
    const salaryMaxValue = document.getElementById("salaryMax").value;
    const experienceValue = document.getElementById("experienceRequired").value;

    const jobData = {
        title: document.getElementById("title").value.trim(),
        description: document.getElementById("description").value.trim(),
        location: document.getElementById("location").value.trim(),
        salaryMin: salaryMinValue ? Number(salaryMinValue) : null,
        salaryMax: salaryMaxValue ? Number(salaryMaxValue) : null,
        experienceRequired: experienceValue ? Number(experienceValue) : null,
        employmentType: document.getElementById("employmentType").value,
        companyId: Number(document.getElementById("companyId").value)
    };

    if (jobData.salaryMin !== null &&
        jobData.salaryMax !== null &&
        jobData.salaryMin > jobData.salaryMax) {
        messageBox.innerHTML =
            '<div class="alert alert-danger">Minimum salary cannot be greater than maximum salary.</div>';
        return;
    }

    try {
        const response = await fetch("/api/jobs", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + token
            },
            body: JSON.stringify(jobData)
        });

        const result = await response.json().catch(() => null);

        if (response.ok) {
            messageBox.innerHTML =
                '<div class="alert alert-success">Job posted successfully!</div>';

            createJobForm.reset();
        } else {
            messageBox.innerHTML =
                `<div class="alert alert-danger">${result?.message || "Failed to create job."}</div>`;
        }
    } catch (error) {
        messageBox.innerHTML =
            '<div class="alert alert-danger">Server connection failed.</div>';
        console.error(error);
    }
});

document.getElementById("logoutBtn").addEventListener("click", function () {
    localStorage.removeItem("token");
    localStorage.removeItem("user");
    window.location.href = "login.html";
});
