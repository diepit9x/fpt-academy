document.querySelector(".content").addEventListener("submit", function (event) {
  event.preventDefault();
});

function validateCreateForm() {
  let isValid = true;

  const namePoll = document.querySelector('input[name="namePoll"]').value;
  if (!namePoll || namePoll.length < 3 || namePoll.length > 255) {
    isValid = false;
    const errorSpan = document.querySelector(".error-namePoll");
    errorSpan.textContent = "Name poll must be from 3 to 255 characters.";
  } else {
    // Xóa thông báo lỗi nếu có
    const errorSpan = document.querySelector(".error-namePoll");
    errorSpan.textContent = "";
  }

  const questions = document.querySelectorAll("#input-question-group .input-question");
  const questionArray = [];

  questions.forEach((question) => {
    // Lấy dữ liệu câu hỏi
    const questionText = question.querySelector('input[name="yourQuestions[]"]').value;
    const mandatory = question.querySelector('input[name="mandatory[]"]').checked;
    const multipleOption = question.querySelector('input[name="multipleOption[]"]').checked;
    const answers = question.querySelectorAll('input[name="answers[]"]');

    // Lưu trữ câu hỏi và các câu trả lời
    const answersArray = Array.from(answers).map((answer) => answer.value);

    // Kiểm tra tính hợp lệ
    if (!questionText || questionText.length < 3 || questionText.length > 255) {
      isValid = false;
      const errorSpan = question.querySelector(".error-yourQuestions");
      errorSpan.textContent = "Question must be from 3 to 255 characters.";
    } else {
      // Xóa thông báo lỗi nếu có
      const errorSpan = question.querySelector(".error-yourQuestions");
      errorSpan.textContent = "";
    }

    // Kiểm tra từng câu trả lời
    answersArray.forEach((answer, index) => {
      const answerInput = answers[index];
      const errorSpan = question.querySelectorAll(".error-answers")[index];
      if (!answer || answer.length < 3 || answer.length > 200) {
        isValid = false;
        errorSpan.textContent = "Answer must be from 3 to 200 characters.";
      } else {
        errorSpan.textContent = "";
      }
    });

    questionArray.push({
      mandatory: mandatory,
      multipleOption: multipleOption,
      question: questionText,
      answers: answersArray,
    });
  });

  if (!isValid) {
    return;
  }

  const data = {
    id: new Date().getTime(),
    namePoll: namePoll,
    questions: questionArray,
  };

  questionList.push(data);
  console.log(data);
  debugger;
  alert("Retain successfully");
  // window.location.href = "";
}
