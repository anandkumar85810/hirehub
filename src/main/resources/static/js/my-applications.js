
const applicationsContainer = document.getElementById("applicationsContainer");
const messageBox = document.getElementById("message");

let allApplications = [];

document.addEventListener("DOMContentLoaded", function () {
    loadMyApplications();

    document.getElementById("statusFilter").addEventListener("change", function () {
        filterApplications(this.value);
    });

    document.getElementById("logoutBtn").addEventListener("click", function () {
        localStorage.removeItem("token");
        localStorage.removeItem("user");
        window.location.href = "../login.html";
    });
});

async function loadMyApplications() {
    const token = localStorage.getItem("token");

    if (!token) {
        messageBox.innerHTML =
            '<div class="alert alert-warning">Please login to view your applications.</div>';
        applicationsContainer.innerHTML = "";
        return;
    }

    try {
        const response = await fetch("/api/job-applications/my-applications", {
            method: "GET",
            headers: {
                "Authorization": "Bearer " + token
            }
        });

        if (!response.ok) {
            throw new Error("Failed to load applications");
        }

        const data = await response.json();

        allApplications = Array.isArray(data) ? data : (data.content || []);

        updateSummary(allApplications);
        displayApplications(allApplications);

    } catch (error) {
        console.error(error);
        messageBox.innerHTML =
            '<div class="alert alert-danger">Could not load applications. Check the API endpoint.</div>';
        applicationsContainer.innerHTML = "";
    }
}

function filterApplications(status) {
    if (status === "ALL") {
        displayApplications(allApplications);
        return;
    }

    const filtered = allApplications.filter(application =>
        (application.status || "").toUpperCase() === status
    );

    displayApplications(filtered);
}

function updateSummary(applications) {
    document.getElementById("totalApplications").textContent =
        applications.length;

    document.getElementById("pendingApplications").textContent =
        applications.filter(app =>
            (app.status || "").toUpperCase() === "PENDING"
        ).length;

    document.getElementById("selectedApplications").textContent =
        applications.filter(app =>
            (app.status || "").toUpperCase() === "SELECTED"
        ).length;
}

function displayApplications(applications) {
    applicationsContainer.innerHTML = "";
    messageBox.innerHTML = "";

    if (!applications.length) {
        applicationsContainer.innerHTML = `
            <div class="text-center py-5">
                <h5>No applications found</h5>
                <p class="text-muted">You have not applied to any jobs yet.</p>
                <a href="jobs.html" class="btn btn-primary">Explore Jobs</a>
            </div>
        `;
        return;
    }

    applications.forEach(application => {
        const job = application.job || {};
        const company = job.company || {};

        const card = document.createElement("div");
        card.className = "border rounded-3 p-3 mb-3";

        const title = document.createElement("h5");
        title.className = "fw-bold mb-1";
        title.textContent = job.title || application.jobTitle || "Job Application";

        const companyName = document.createElement("p");
        companyName.className = "text-muted mb-2";
        companyName.textContent =
            company.name || application.companyName || "Company";

        const details = document.createElement("p");
        details.className = "small text-muted mb-2";

        const location = job.location || application.location || "Location not specified";
        const appliedDate = formatDate(application.appliedAt || application.createdAt);

        details.textContent = `📍 ${location}  •  Applied: ${appliedDate}`;

        const status = document.createElement("span");
        status.className = "badge " + getStatusClass(application.status);
        status.textContent = application.status || "UNKNOWN";

        const actions = document.createElement("div");
        actions.className = "d-flex flex-wrap gap-2 mt-3";

        const detailsLink = document.createElement("a");
        detailsLink.className = "btn btn-sm btn-outline-primary";
        detailsLink.href =
            `../job-details.html?id=${encodeURIComponent(job.id || application.jobId || "")}`;
        detailsLink.textContent = "View Job";

        actions.appendChild(detailsLink);

        card.append(title, companyName, details, status, actions);
        applicationsContainer.appendChild(card);
    });
}

function getStatusClass(status) {
    switch ((status || "").toUpperCase()) {
        case "SELECTED":
            return "bg-success";
        case "REJECTED":
            return "bg-danger";
        case "SHORTLISTED":
        case "INTERVIEW":
            return "bg-info text-dark";
        case "REVIEWED":
            return "bg-primary";
        case "PENDING":
            return "bg-warning text-dark";
        default:
            return "bg-secondary";
    }
}

function formatDate(dateValue) {
    if (!dateValue) {
        return "Not available";
    }

    const date = new Date(dateValue);

    if (Number.isNaN(date.getTime())) {
        return dateValue;
    }

    return date.toLocaleDateString("en-IN", {
        day: "2-digit",
        month: "short",
        year: "numeric"
    });
}
