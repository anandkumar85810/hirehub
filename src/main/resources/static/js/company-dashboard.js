
const recentJobsContainer = document.getElementById("recentJobs");
const messageBox = document.getElementById("message");

document.addEventListener("DOMContentLoaded", function () {
    loadCompanyDashboard();

    document.getElementById("logoutBtn").addEventListener("click", function () {
        localStorage.removeItem("token");
        localStorage.removeItem("user");
        window.location.href = "../login.html";
    });
});

async function loadCompanyDashboard() {
    const token = localStorage.getItem("token");

    if (!token) {
        showMessage("Please login first.", "warning");
        recentJobsContainer.innerHTML = "";
        return;
    }

    try {
        const response = await fetch("/api/jobs/company/my-jobs", {
            method: "GET",
            headers: {
                "Authorization": "Bearer " + token
            }
        });

        if (!response.ok) {
            throw new Error("Unable to load company jobs");
        }

        const data = await response.json();
        const jobs = Array.isArray(data) ? data : (data.content || []);

        updateDashboardStats(jobs);
        displayRecentJobs(jobs);

    } catch (error) {
        console.error(error);
        showMessage(
            "Could not load dashboard data. Please verify the jobs API endpoint.",
            "danger"
        );
        recentJobsContainer.innerHTML = "";
    }
}

function updateDashboardStats(jobs) {
    document.getElementById("totalJobs").textContent = jobs.length;

    const activeJobs = jobs.filter(job =>
        (job.status || "").toUpperCase() === "ACTIVE"
    ).length;

    document.getElementById("activeJobs").textContent = activeJobs;

    // Application totals depend on the job response including application counts.
    const totalApplications = jobs.reduce((total, job) => {
        return total + Number(job.applicationCount || job.applicationsCount || 0);
    }, 0);

    const shortlistedApplications = jobs.reduce((total, job) => {
        return total + Number(job.shortlistedCount || 0);
    }, 0);

    document.getElementById("totalApplications").textContent = totalApplications;
    document.getElementById("shortlistedApplications").textContent =
        shortlistedApplications;
}

function displayRecentJobs(jobs) {
    recentJobsContainer.innerHTML = "";

    if (!jobs.length) {
        recentJobsContainer.innerHTML = `
            <div class="text-center py-4">
                <h6>No jobs posted yet</h6>
                <p class="text-muted">Create your first job post to start receiving applications.</p>
                <a href="create-job.html" class="btn btn-primary">Post a Job</a>
            </div>
        `;
        return;
    }

    jobs.slice(0, 5).forEach(job => {
        const row = document.createElement("div");
        row.className =
            "d-flex flex-wrap justify-content-between align-items-center border-bottom py-3 gap-3";

        const info = document.createElement("div");

        const title = document.createElement("h6");
        title.className = "fw-bold mb-1";
        title.textContent = job.title || "Job Opening";

        const location = document.createElement("p");
        location.className = "text-muted small mb-1";
        location.textContent = job.location || "Location not specified";

        const status = document.createElement("span");
        status.className = "badge " + getStatusClass(job.status);
        status.textContent = job.status || "UNKNOWN";

        info.append(title, location, status);

        const actions = document.createElement("div");
        actions.className = "d-flex gap-2";

        const viewLink = document.createElement("a");
        viewLink.className = "btn btn-sm btn-outline-primary";
        viewLink.href = `jobs.html?id=${encodeURIComponent(job.id)}`;
        viewLink.textContent = "Manage";

        actions.appendChild(viewLink);
        row.append(info, actions);
        recentJobsContainer.appendChild(row);
    });
}

function getStatusClass(status) {
    switch ((status || "").toUpperCase()) {
        case "ACTIVE":
            return "bg-success";
        case "CLOSED":
            return "bg-secondary";
        case "DRAFT":
            return "bg-warning text-dark";
        default:
            return "bg-primary";
    }
}

function showMessage(message, type) {
    messageBox.innerHTML = "";

    const alert = document.createElement("div");
    alert.className = `alert alert-${type}`;
    alert.textContent = message;

    messageBox.appendChild(alert);
}
