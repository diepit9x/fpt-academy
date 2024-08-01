document.querySelector(".form-submit, .main-body").addEventListener("submit", function (event) {
  debugger;
  event.preventDefault();
});

function validateLoginForm() {
  let isValid = true;

  // Clear previous error messages
  document.getElementById("emailError").textContent = "";
  document.getElementById("passwordError").textContent = "";

  // Validate Email
  const email = document.getElementById("email").value;
  const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
  if (!emailPattern.test(email)) {
    document.getElementById("emailError").textContent = "Invalid email address format";
    isValid = false;
  }
  if (email.length > 50) {
    document.getElementById("emailError").textContent = "Username must be from 8 to 50 characters";

    isValid = false;
  }

  // Validate Password
  const password = document.getElementById("password").value;
  if (password.length < 8 || password.length > 30) {
    document.getElementById("passwordError").textContent = "Password must be from 8 and 30 characters";
    isValid = false;
  }

  // If any field is invalid, prevent form submission
  if (!isValid) {
    return;
  }
  if (email == "diepit9x@gmail.com" && password == "123123123") {
    window.location.href = "/cms.html";
    return;
  }
  alert("wrong email/password");
}

function validateRegisterForm() {
  let isValid = true;

  // Clear previous error messages
  document.getElementById("usernameError").textContent = "";
  document.getElementById("emailError").textContent = "";
  document.getElementById("passwordError").textContent = "";
  document.getElementById("rePasswordError").textContent = "";

  // Validate Username
  const username = document.getElementById("username").value;
  const usernamePattern = /^[a-zA-Z0-9]{3,30}$/;
  if (!usernamePattern.test(username)) {
    document.getElementById("usernameError").textContent = "Username must be from 3 to 50 characters";
    isValid = false;
  }
  // Validate Email
  const email = document.getElementById("email").value;
  const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
  if (!emailPattern.test(email)) {
    document.getElementById("emailError").textContent = "Invalid email address format";
    isValid = false;
  }
  if (email.length > 50) {
    document.getElementById("emailError").textContent = "Username must be from 8 to 50 characters";
    isValid = false;
  }

  // Validate Password
  const password = document.getElementById("password").value;
  if (password.length < 8 || password.length > 30) {
    document.getElementById("passwordError").textContent = "Password must be from 8 and 30 characters";
    isValid = false;
  }

  // Validate RePassword
  const rePassword = document.getElementById("rePassword").value;
  if (rePassword.length < 8 || rePassword.length > 30) {
    document.getElementById("rePasswordError").textContent = "Password must be from 8 and 30 characters";
    isValid = false;
  }

  if (rePassword != password) {
    document.getElementById("rePasswordError").textContent = "Re-enter password does not match";
    isValid = false;
  }

  // If any field is invalid, prevent form submission
  if (!isValid) {
    return;
  }
  window.location.href = "/login.html";
}

function validateEditProfile() {
  let isValid = true;

  // Clear previous error messages
  document.getElementById("firstNameError").textContent = "";
  document.getElementById("lastNameError").textContent = "";
  document.getElementById("phoneError").textContent = "";
  document.getElementById("descriptionError").textContent = "";

  // Validate FirstName, Lastname
  const firstName = document.getElementById("firstName").value;
  const namePattern = /^[a-zA-Z0-9]{3,30}$/;
  if (!namePattern.test(firstName)) {
    document.getElementById("firstNameError").textContent = "FirstName must be from 3 to 30 characters";
    isValid = false;
  }
  const lastName = document.getElementById("lastName").value;
  if (!namePattern.test(lastName)) {
    document.getElementById("lastNameError").textContent = "LastName must be from 3 to 30 characters";
    isValid = false;
  }

  //Validate phone
  const phone = document.getElementById("phone").value;
  const phonePattern = /^[0-9]{9,13}$/;
  if (!phonePattern.test(phone)) {
    document.getElementById("phoneError").textContent = "Phone must be from 9 to 13 numbers";
    isValid = false;
  }

  // Validate description
  const description = document.getElementById("description").value;
  if (description.length > 200) {
    document.getElementById("descriptionError").textContent = "Maximum 200 characters";
    isValid = false;
  }

  // If any field is invalid, prevent form submission
  if (!isValid) {
    return;
  }
  alert("Update Profile successfully");
}

function validateFormContent() {
  let isValid = true;

  // Clear previous error messages
  document.getElementById("titleError").textContent = "";
  document.getElementById("briefError").textContent = "";
  document.getElementById("contentError").textContent = "";

  // Validate title
  const title = document.getElementById("title").value;
  if (title.length < 10 || title.length > 200) {
    document.getElementById("titleError").textContent = "Title must be from 10 to 200 characters";
    isValid = false;
  }

  // Validate brief
  const brief = document.getElementById("brief").value;
  if (brief.length < 30 || brief.length > 150) {
    document.getElementById("briefError").textContent = "Brief must be from 30 to 150 characters";
    isValid = false;
  }
  debugger;
  // Validate content
  const content = document.getElementById("content").value;
  if (content.length < 50 || content.length > 1000) {
    document.getElementById("contentError").textContent = "Content must be from 50 to 1000 characters";
    isValid = false;
  }

  // If any field is invalid, prevent form submission
  if (!isValid) {
    return;
  }
  alert("Add content successfully");
}
