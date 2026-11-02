
const API_BASE = "http://localhost:8080/api";

const token = localStorage.getItem("token");

const jobsTableBody = document.getElementById("jobsTableBody");
const searchInput = document.getElementById("searchInput");
const statusFilter = document.getElementById("statusFilter");
const messageBox = document.getElementById("messageBox");

let allJobs = [];

function getHeaders() {
    return {
        "Content-Type": "application/json",
        "Authorization": `Bearer ${token}`
    };
}

function showMessage(message, type = "danger") {
    messageBox.innerHTML = `
        <div class="alert alert-${type}">
            ${message}
        </div>
    `;
}

function formatSalary(min, max) {
    if (min == null && max == null) return "Not specified";
    if (min != null && max != null) {
        return `₹${Number(min).toLocaleString("en-IN")} - ₹${Number(max).toLocaleString("en-IN")}`;
    }
    if (min != null) {
        return `₹${Number(min).toLocaleString("en-IN")}+`;
    }
    return `Up to ₹${Number(max).toLocaleString("en-IN")}`;
}

function formatDate(dateValue) {
    if (!dateValue) return "—";

    const date = new Date(dateValue);

    if (Number.isNaN(date.getTime())) return "—";

    return date.toLocaleDateString("en-IN", {
        day: "2-digit",
        month: "short",
        year: "numeric"
    });
}

function getStatusClass(status) {
    switch ((status || "").toUpperCase()) {
        case "ACTIVE":
            return "status-active";
        case "INACTIVE":
            return "status-inactive";
        case "CLOSED":
            return "status-closed";
        default:
            return "status-inactive";
    }
}

function updateStats(jobs) {
    document.getElementById("totalJobs").textContent = jobs.length;

    document.getElementById("activeJobs").textContent =
        jobs.filter(job => job.status === "ACTIVE").length;

    document.getElementById("inactiveJobs").textContent =
        jobs.filter(job => job.status === "INACTIVE").length;

    document.getElementById("closedJobs").textContent =
        jobs.filter(job => job.status === "CLOSED").length;
}

function renderJobs(jobs) {
    if (!jobs.length) {
        jobsTableBody.innerHTML = `
            <tr>
                <td colspan="7" class="text-center py-4 text-muted">
                    No jobs found.
                </td>
            </tr>
        `;
        return;
    }

    jobsTableBody.innerHTML = jobs.map(job => `
        <tr>
            <td>
                <div class="job-title">${job.title ?? "Untitled Job"}</div>
                <small class="text-muted">Job ID: ${job.id}</small>
            </td>

            <td>${job.location ?? "—"}</td>
            <td>${job.employmentType ?? "—"}</td>
            <td>${formatSalary(job.salaryMin, job.salaryMax)}</td>

            <td>
                <span class="status-badge ${getStatusClass(job.status)}">
                    ${job.status ?? "UNKNOWN"}
                </span>
            </td>

            <td>${formatDate(job.createdAt ?? job.postedAt)}</td>

            <td>
                <div class="action-buttons">
                    <a href="edit-job.html?id=${job.id}"
                       class="btn btn-sm btn-warning">
                        Edit
                    </a>

                    <button class="btn btn-sm btn-danger"
                            onclick="deleteJob(${job.id})">
                        Delete
                    </button>
                </div>
            </td>
        </tr>
    `).join("");
}

function applyFilters() {
    const searchTerm = searchInput.value.trim().toLowerCase();
    const selectedStatus = statusFilter.value;

    const filteredJobs = allJobs.filter(job => {
        const title = (job.title ?? "").toLowerCase();
        const location = (job.location ?? "").toLowerCase();
        const matchesSearch =
            title.includes(searchTerm) || location.includes(searchTerm);

        const matchesStatus =
            !selectedStatus || job.status === selectedStatus;

        return matchesSearch && matchesStatus;
    });

    renderJobs(filteredJobs);
}

async function loadMyJobs() {
    if (!token) {
        window.location.href = "../login.html";
        return;
    }

    try {
        // Assumed endpoint: replace with your actual JobController route if different.
        const response = await fetch(`${API_BASE}/jobs/company/my-jobs`, {
            method: "GET",
            headers: getHeaders()
        });

        if (!response.ok) {
            throw new Error("Unable to load your jobs.");
        }

        const data = await response.json();

        // Supports either a direct array or Spring Page response.
        allJobs = Array.isArray(data) ? data : (data.content ?? []);

        updateStats(allJobs);
        applyFilters();

    } catch (error) {
        jobsTableBody.innerHTML = `
            <tr>
                <td colspan="7" class="text-center text-danger py-4">
                    ${error.message}
                </td>
            </tr>
        `;
    }
}

async function deleteJob(jobId) {
    const confirmed = confirm("Are you sure you want to delete this job?");
    if (!confirmed) return;

    try {
        const response = await fetch(`${API_BASE}/jobs/${jobId}`, {
            method: "DELETE",
            headers: getHeaders()
        });

        if (!response.ok) {
            const errorText = await response.text();
            throw new Error(errorText || "Job delete nahi ho payi.");
        }

        showMessage("Job deleted successfully.", "success");
        await loadMyJobs();

    } catch (error) {
        showMessage(error.message);
    }
}

searchInput.addEventListener("input", applyFilters);
statusFilter.addEventListener("change", applyFilters);

document.getElementById("logoutBtn").addEventListener("click", function (event) {
    event.preventDefault();

    localStorage.removeItem("token");
    localStorage.removeItem("user");

    window.location.href = "../login.html";
});

loadMyJobs();
