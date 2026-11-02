const jobsContainer = document.getElementById("jobsContainer");
const messageBox = document.getElementById("message");
const jobCount = document.getElementById("jobCount");

let allJobs = [];

document.addEventListener("DOMContentLoaded", function () {
    loadJobs();

    document.getElementById("searchForm").addEventListener("submit", function (event) {
        event.preventDefault();
        filterJobs();
    });

    document.getElementById("logoutBtn").addEventListener("click", function () {
        localStorage.removeItem("token");
        localStorage.removeItem("user");
        window.location.href = "../login.html";
    });
});

async function loadJobs() {
    const token = localStorage.getItem("token");

    try {
        const response = await fetch("/api/jobs", {
            method: "GET",
            headers: token ? {
                "Authorization": "Bearer " + token
            } : {}
        });

        if (!response.ok) {
            throw new Error("Unable to fetch jobs");
        }

        const data = await response.json();

        // Supports either an array or a paginated response
        allJobs = Array.isArray(data) ? data : (data.content || []);

        displayJobs(allJobs);
    } catch (error) {
        console.error(error);
        jobCount.textContent = "0 jobs";

        messageBox.innerHTML =
            '<div class="alert alert-danger">Unable to load jobs. Please try again later.</div>';
    }
}

function filterJobs() {
    const keyword = document.getElementById("searchKeyword")
        .value.trim().toLowerCase();

    const location = document.getElementById("searchLocation")
        .value.trim().toLowerCase();

    const filteredJobs = allJobs.filter(function (job) {
        const title = (job.title || "").toLowerCase();
        const jobLocation = (job.location || "").toLowerCase();

        return title.includes(keyword) && jobLocation.includes(location);
    });

    displayJobs(filteredJobs);
}

function displayJobs(jobs) {
    jobsContainer.innerHTML = "";
    messageBox.innerHTML = "";
    jobCount.textContent = `${jobs.length} job(s) found`;

    if (!jobs.length) {
        jobsContainer.innerHTML =
            '<div class="card border-0 shadow-sm"><div class="card-body text-center py-5"><h5>No jobs found</h5><p class="text-muted mb-0">Try another keyword or location.</p></div></div>';
        return;
    }

    jobs.forEach(function (job) {
        const card = document.createElement("div");
        card.className = "card border-0 shadow-sm mb-3";

        const body = document.createElement("div");
        body.className = "card-body p-4";

        const title = document.createElement("h5");
        title.className = "fw-bold mb-1";
        title.textContent = job.title || "Job Opening";

        const company = document.createElement("p");
        company.className = "text-muted mb-2";
        company.textContent =
            job.companyName || job.company?.name || "Company";

        const details = document.createElement("div");
        details.className = "d-flex flex-wrap gap-3 text-muted small mb-3";

        const location = document.createElement("span");
        location.textContent = "📍 " + (job.location || "Not specified");

        const salary = document.createElement("span");
        salary.textContent = formatSalary(job.salaryMin, job.salaryMax);

        const type = document.createElement("span");
        type.textContent = job.employmentType || "Employment type not specified";

        details.append(location, salary, type);

        const description = document.createElement("p");
        description.className = "mb-3";
        description.textContent = job.description
            ? job.description.substring(0, 180) + (job.description.length > 180 ? "..." : "")
            : "No description available.";

        const actions = document.createElement("div");
        actions.className = "d-flex gap-2";

        const detailsLink = document.createElement("a");
        detailsLink.className = "btn btn-outline-primary";
        detailsLink.href = `../job-details.html?id=${encodeURIComponent(job.id)}`;
        detailsLink.textContent = "View Details";

        const applyLink = document.createElement("a");
        applyLink.className = "btn btn-primary";
        applyLink.href = `../job-details.html?id=${encodeURIComponent(job.id)}`;
        applyLink.textContent = "Apply Now";

        actions.append(detailsLink, applyLink);
        body.append(title, company, details, description, actions);
        card.appendChild(body);
        jobsContainer.appendChild(card);
    });
}

function formatSalary(min, max) {
    if (min == null && max == null) {
        return "Salary not specified";
    }

    const formatAmount = value =>
        "₹" + Number(value).toLocaleString("en-IN");

    if (min != null && max != null) {
        return `${formatAmount(min)} - ${formatAmount(max)}`;
    }

    if (min != null) {
        return `From ${formatAmount(min)}`;
    }

    return `Up to ${formatAmount(max)}`;
}
