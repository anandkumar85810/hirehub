const registerForm = document.getElementById("registerForm");
registerForm.addEventListener("submit", async function (event) {
    //Prevent page reload
    event.preventDefault();

    //1. Get values from form
    const username = document.getElementById("username").value.trim();
    const email = document.getElementById("email").value.trim();
    const phone = document.getElementById("phone").value.trim();
    const password = document.getElementById("password").value;
    const role = document.getElementById("role").value;

    //2. Basic frontend validation
    if (!username || !email || !phone || !password || !role) {
        alert("Please fill all fields");
        return;
    }

    //3. Prepare request body
    const registerData = {
        username: username,
        email: email,
        phone: phone,
        password: password,
        role: "CANDIDATE"
    };

    //4. Send API request
    try {
        const data = await apiRequest(
            "/api/auth/register",
            "POST",
            registerData
        );
        console.log("Registration Successful");

        alert("Registration successful! Please login.");

        //5. Redirect to login page
        window.location.href = "login.html";
    } catch (error) {
        console.error("Registration error:", error);
        alert(error.message || "Registration field");
    }
})