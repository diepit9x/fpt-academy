var questionList = [];

$(document).ready(function () {
  $(".content").load("pages/create.html");

  $(".load-page").click(function (e) {
    e.preventDefault(); // Ngăn chặn hành vi mặc định của thẻ <a>
    var url = $(this).attr("ref"); // Lấy giá trị của thuộc tính ref
    $.ajax({
      type: "GET",
      url: url,
      success: function (html) {
        $(".content").html(html);
      },
      error: function () {
        $(".content").html("<h1>404 NOT FOUND<h1>");
      },
    });
  });
});

function addAnswer(button) {
  // Tìm phần tử cha chứa nút "Add"
  const questionGroup = button.closest(".form-group.row"); // Tìm nhóm câu hỏi
  const inputAnswerContainer = questionGroup.querySelector("#input-answer-container"); // Tìm container câu trả lời

  if (!inputAnswerContainer) {
    console.error("Không tìm thấy input-answer-container.");
    return; // Ngừng nếu không tìm thấy container
  }
  // Sao chép nhóm input hiện tại
  const currentInputGroup = inputAnswerContainer.querySelector(".input-answer:last-child");
  const newInputGroup = currentInputGroup.cloneNode(true); // Sao chép toàn bộ

  // Xóa input-group-append hiện tại
  const currentAppend = currentInputGroup.querySelector(".input-group-append");
  if (currentAppend) {
    currentAppend.remove();
  }

  // Reset giá trị của input mới
  const newInput = newInputGroup.querySelector("input");
  newInput.value = ""; // Xóa giá trị của input mới
  newInput.placeholder = "";

  const resetSpan = newInputGroup.querySelectorAll("span.text-danger");
  resetSpan.forEach((element) => {
    element.textContent = "";
  });

  // Thêm nhóm input mới vào container
  inputAnswerContainer.appendChild(newInputGroup);
}

function addQuestion() {
  const container = document.getElementById("input-question-group");
  // Sao chép nhóm input hiện tại
  const lastInputQuestion = container.lastElementChild;
  const newInputQuestion = lastInputQuestion.cloneNode(true); // Sao chép toàn bộ

  // Xóa input-group-append mới
  const inputAnswers = newInputQuestion.querySelectorAll(".input-answer:not(:last-child)");
  inputAnswers.forEach((element) => {
    element.remove();
  });

  const inputAnswer = newInputQuestion.querySelector(".input-group > input");
  if (inputAnswer) {
    inputAnswer.placeholder = "Type your answer";
  }

  // reset data
  const resetInput = newInputQuestion.querySelectorAll("input");
  resetInput.forEach((element) => {
    if (element.type === "checkbox") {
      element.checked = false; // Đặt lại ô checkbox
    } else {
      element.value = ""; // Đặt lại các loại input khác
    }
  });

  const resetSpan = newInputQuestion.querySelectorAll("span.text-danger");
  resetSpan.forEach((element) => {
    element.textContent = "";
  });

  // Xóa button add question hiện tại
  const currentAppend = lastInputQuestion.querySelector(".input-question > :last-child");
  if (currentAppend) {
    currentAppend.remove();
  }

  // Thêm nhóm input mới vào container
  container.appendChild(newInputQuestion);
}

function deletePoll(id) {
  const confirmDelete = window.confirm("Are you sure you want to delete this item ?");
  if (confirmDelete) {
    $.ajax({
      // type: "GET",
      // url: "",
      success: function (html) {
        alert("Delete successfully");
        questionList = questionList.filter((question) => question.id !== id);
        showQuestionList();
      },
      error: function () {
        alert("Delete failure");
      },
    });
  } else {
    alert("Delete cancelled");
  }
}

function showQuestionList() {
  const tableBody = document.getElementById("table-body");
  tableBody.innerHTML = "";
  questionList.forEach((question) => {
    const row = document.createElement("tr");
    row.innerHTML = `
      <td>${question.id}</td>
      <td>${question.namePoll}</td>
      <td>
        <button type="button" class="btn btn-input btn-sm">View results</button>
        <button type="button" class="btn btn-input btn-sm">Close poll</button>
        <button type="button" onclick="deletePoll(${question.id})" class="btn btn-input btn-sm">Delete</button>
      </td>
  `;
    tableBody.appendChild(row);
  });
}

// random poll
function getRandomPoll() {
  const randomIndex = Math.floor(Math.random() * questionList.length);
  return questionList[randomIndex];
}

// show vote
function showVote() {
  if (!questionList) {
    return;
  }
  const poll = getRandomPoll();
  const form = document.querySelector(".input-group");

  // Xóa nội dung hiện tại
  form.innerHTML = "";

  // Tạo tiêu đề
  const title = document.createElement("h1");
  title.className = "pb-2";
  title.textContent = poll.namePoll;
  form.appendChild(title);

  // Tạo danh sách câu hỏi
  const questionListElement = document.createElement("ol");

  poll.questions.forEach((question) => {
    const listItem = document.createElement("li");

    // Thêm dấu * đỏ nếu question.mandatory là true
    const questionText = document.createElement("span");
    questionText.textContent = question.question;
    if (question.mandatory) {
      questionText.innerHTML += ' <span style="color: red;">*</span>'; // Dấu * đỏ
    }
    listItem.appendChild(questionText);

    // Tạo danh sách câu trả lời
    const answerList = document.createElement("ul");
    answerList.className = "list-unstyled pb-3";

    question.answers.forEach((answer) => {
      const answerItem = document.createElement("li");
      const input = document.createElement("input");
      input.type = question.multipleOption ? "checkbox" : "radio"; // Thiết lập loại input
      input.name = question.mandatory ? "mandatory" : "optional"; // Đặt name cho input
      input.value = answer;

      const label = document.createElement("label");
      label.textContent = answer;

      answerItem.appendChild(input);
      answerItem.appendChild(label);
      answerList.appendChild(answerItem);
    });

    listItem.appendChild(answerList);
    questionListElement.appendChild(listItem);
  });

  form.appendChild(questionListElement);

  // Tạo nút gửi
  const submitBtn = document.createElement("input");
  submitBtn.type = "submit";
  submitBtn.value = "Retain";
  form.appendChild(submitBtn);
}
