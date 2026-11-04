const API_BASE_URL = "/api";

async function loadJobs() {
    try {
        const response = await fetch(`${API_BASE_URL}/jobs`);

        if(!response.ok) {
            throw new Error("Jobs fetch failed");
        }

        const jobs = await response.JSON();

        const.log(jobs);
    } catch (error) {
        console.error(error);
    }
}

loadJobs();