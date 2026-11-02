const loginForm = document.getElementById("loginForm");

    loginForm.addEventListener("submit", async function (event) {
    event.preventDefault();

    console.log("Login form submitted")

    //1. Input fields se values lena
    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value;

    //2. API request bhejna
    try{
        const response = await fetch("/api/auth/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                email: email,
                password: password
            })
        });

        //3. API response read karna
        const data = await response.json();

        console.log("Login response:", data);
        console.log("Status:", response.status);

        //4. Login fail hone par
        if(!response.ok) {
            alert(data.message || data.error || "Invalid email or password")
            return;
        }

        // Token mila ya nhi check karna
        if(!data.token) {
            alert("Login successful, but token was not received.");
            return;
        }


        //5. Save JWT token
        localStorage.setItem("token", data.token);

        //6. Save user details (response fields are top-level)
        const user = {
            userId: data.userId,
            username: data.username,
            email: data.email,
            role: data.role
        };

        localStorage.setItem("user", JSON.stringify(user));

        //7 Role-based redirect
        const role = data.role?.toUpperCase();

        if (role === "COMPANY") {
            window.location.href = "company/dashboard.html";
        } else if (role === "CANDIDATE") {
            window.location.href = "candidate/dashboard.html";
        } else if (role === "ADMIN") {
            window.location.href = "admin/dashboard.html";
        } else {
            window.location.href = "index.html";
        }
    } catch (error) {
        console.error("Login error:", error);
        alert("Server is not connected");
    }
});