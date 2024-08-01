function searchVisitor() {
  var keyword = document.getElementById("keyword").value;

  visitors = getVisitors();

  const tableBody = document.getElementById("table-body");
  tableBody.innerHTML = ""; // Xóa nội dung hiện tại của tbody

  visitors.forEach((visitor) => {
    if (!keyword || keyword != null) {
      // Chuyển đổi đối tượng thành chuỗi
      const visitorString = JSON.stringify(visitor).toLowerCase();
      if (visitorString.includes(keyword.toLowerCase())) {
        const row = document.createElement("tr");
        row.innerHTML = `
          <td>${visitor.firstName}</td>
          <td>${visitor.lastName}</td>
          <td>${visitor.gender}</td>
          <td>${visitor.telephone}</td>
          <td>${visitor.youAreIn}</td>
          <td>${visitor.hobbies}</td>
          <td>${visitor.description}</td>
          `;

        tableBody.appendChild(row);
      }
    } else {
      const row = document.createElement("tr");
      row.innerHTML = `
        <td>${visitor.firstName}</td>
        <td>${visitor.lastName}</td>
        <td>${visitor.gender}</td>
        <td>${visitor.telephone}</td>
        <td>${visitor.youAreIn}</td>
        <td>${visitor.hobbies}</td>
        <td>${visitor.description}</td>
    `;

      tableBody.appendChild(row);
    }
  });
}

function register() {
  let isValid = true;

  const namePattern = /^[a-zA-Z]{1,20}$/;
  const phonePattern = /^[0-9]{1,11}$/;
  const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}(?=.{0,50}$)/;

  //validate name
  const firstName = document.getElementById("firstName").value;
  if (!namePattern.test(firstName)) {
    isValid = false;
    alert("First name must be characters and not contain number. Max length: 20");
  }
  //validate name
  const lastName = document.getElementById("lastName").value;
  if (!namePattern.test(lastName)) {
    isValid = false;
    alert("Last name must be characters and not contain number. Max length: 20");
  }
  //validate gender
  const gender = document.getElementById("gender").value;

  //Validate phone
  const telephone = document.getElementById("telephone").value;
  if (!phonePattern.test(telephone)) {
    isValid = false;
    alert("Telephone must be number. Max length: 11");
  }
  //Validate email
  const email = document.getElementById("email").value;
  if (email && !emailPattern.test(email)) {
    alert("Invalid email address format. Max length: 50");
    isValid = false;
  }
  //Validate youAreIn
  const youAreIn = document.querySelector('input[name="youAreIn"]:checked')?.value;
  if (youAreIn == null) {
    alert("Please select your are in");
    isValid = false;
  }

  //Validate hobbies
  const selectedHobbies = Array.from(document.querySelectorAll('input[name="hobbies[]"]:checked')).map((checkbox) => checkbox.value);

  //validate description
  const description = document.getElementById("description").value;
  if (description && description.length > 200) {
    alert("Description must be less than 200 characters");
    isValid = false;
  }
  if (!isValid) {
    return;
  }

  visitor = {
    firstName: firstName,
    lastName: lastName,
    gender: gender,
    telephone: telephone,
    email: email,
    youAreIn: youAreIn,
    hobbies: selectedHobbies,
    description: description,
  };
  addVisitor(visitor);
  alert("Register successfully");
  window.location.href = "";
  debugger;
}

function addVisitor(visitor) {
  // Lấy mảng visitor từ localStorage, nếu không có thì khởi tạo mảng mới
  const visitors = JSON.parse(localStorage.getItem("visitors")) || [];

  // Thêm visitor mới vào mảng
  visitors.push(visitor);

  // Lưu lại mảng vào localStorage
  localStorage.setItem("visitors", JSON.stringify(visitors));
}

function getVisitors() {
  // Lấy mảng visitor từ localStorage
  const visitors = JSON.parse(localStorage.getItem("visitors")) || [];
  return visitors;
}
