
document.addEventListener("DOMContentLoaded", function () {
    const token = localStorage.getItem("token");

    // Redirect to login if token is missing
    if (!token) {
        window.location.href = "../login.html";
        return;
    }

    // Display saved user information if available
    const savedUser = localStorage.getItem("user");

    if (savedUser) {
        try {
            const user = JSON.parse(savedUser);

            document.getElementById("candidateName").textContent =
                user.username || user.name || "Candidate";
        } catch (error) {
            console.error("Unable to read user information", error);
        }
    }

    // Logout
    document.getElementById("logoutBtn").addEventListener("click", function () {
        localStorage.removeItem("token");
        localStorage.removeItem("user");
        window.location.href = "../login.html";
    });

    // API integration will be added after confirming endpoint paths
    loadDashboardData();
});

async function loadDashboardData() {
    const token = localStorage.getItem("token");

    try {
        // TODO: Replace endpoint with your actual jobs API
        const response = await fetch("/api/jobs", {
            method: "GET",
            headers: {
                "Authorization": "Bearer " + token
            }
        });

        if (response.ok) {
            const jobs = await response.json();

            document.getElementById("availableJobsCount").textContent =
                Array.isArray(jobs) ? jobs.length : "--";

            showRecommendedJobs(Array.isArray(jobs) ? jobs.slice(0, 3) : []);
        }
    } catch (error) {
        console.error("Could not load jobs", error);
    }
}

function showRecommendedJobs(jobs) {
    const container = document.getElementById("recommendedJobs");

    if (!jobs.length) {
        container.innerHTML =
            '<p class="text-muted">No jobs available right now.</p>';
        return;
    }

    container.innerHTML = "";

    jobs.forEach(function (job) {
        const card = document.createElement("div");
        card.className = "border rounded-3 p-3 mb-3";

        const title = document.createElement("h5");
        title.className = "fw-bold";
        title.textContent = job.title || "Job Opening";

        const location = document.createElement("p");
        location.className = "text-muted mb-2";
        location.textContent = job.location || "Location not specified";

        const link = document.createElement("a");
        link.className = "btn btn-sm btn-outline-primary";
        link.href = `../job-details.html?id=${encodeURIComponent(job.id)}`;
        link.textContent = "View Details";

        card.appendChild(title);
        card.appendChild(location);
        card.appendChild(link);

        container.appendChild(card);
    });
}
