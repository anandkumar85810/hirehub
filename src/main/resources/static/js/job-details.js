

const applyButton = document.getElementById("applyButton");
const applyMessage = document.getElementById("applyMessage");

// Sample job data for now
const sampleJob = {
    id: 1,
    title: "Java Spring Boot Developer",
    company: "Tech Solutions Pvt Ltd",
    location: "Noida",
    salary: "₹4–7 LPA",
    type: "Full Time",
    description:
        "We are looking for a Java Spring Boot Developer to develop and maintain backend applications and REST APIs.",
    experience: "0–2 years",
    companyDescription:
        "A technology company focused on building modern software solutions.",
    industry: "IT Services",
    companyLocation: "Noida, India",
    website: "https://example.com"
};

// Display sample job details
document.getElementById("jobTitle").textContent = sampleJob.title;
document.getElementById("companyName").textContent = sampleJob.company;
document.getElementById("jobLocation").textContent = "📍 " + sampleJob.location;
document.getElementById("jobSalary").textContent = "💰 " + sampleJob.salary;
document.getElementById("jobType").textContent = "💼 " + sampleJob.type;
document.getElementById("jobDescription").textContent = sampleJob.description;
document.getElementById("jobExperience").textContent = sampleJob.experience;
document.getElementById("jobEmploymentType").textContent = sampleJob.type;
document.getElementById("companyDescription").textContent =
    sampleJob.companyDescription;
document.getElementById("companyIndustry").textContent = sampleJob.industry;
document.getElementById("companyLocation").textContent =
    sampleJob.companyLocation;

const companyWebsite = document.getElementById("companyWebsite");
companyWebsite.href = sampleJob.website;
companyWebsite.textContent = sampleJob.website;

// Apply button (backend integration will be added later)
applyButton.addEventListener("click", function () {
    const token = localStorage.getItem("token");

    if (!token) {
        applyMessage.innerHTML =
            '<div class="alert alert-warning">Please login as a candidate to apply.</div>';

        setTimeout(() => {
            window.location.href = "login.html";
        }, 1500);

        return;
    }

    applyMessage.innerHTML =
        '<div class="alert alert-info">Application API will be connected in the next step.</div>';
});
