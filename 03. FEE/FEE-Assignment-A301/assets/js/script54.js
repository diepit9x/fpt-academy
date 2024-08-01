var items = [
  {
    name: "Cake",
    img: "assets/img/cake.jpg",
    price: 10,
  },
  {
    name: "Cake",
    img: "assets/img/cake-2.jfif",
    price: 10,
  },
  {
    name: "Cupcake",
    img: "assets/img/cake.jpg",
    price: 10,
  },
  {
    name: "Sweet",
    img: "assets/img/cake.jpg",
    price: 10,
  },
  {
    name: "Cake",
    img: "assets/img/cake.jpg",
    price: 10,
  },
  {
    name: "Doughnut",
    img: "assets/img/cake.jpg",
    price: 10,
  },
];

const search = document.getElementById("search");
search.addEventListener("keyup", function (e) {
  const searchText = search.value.toLowerCase();
  const filteredItems = items.filter((item) => item.name.toLowerCase().includes(searchText));
  displayItems(filteredItems);
});

function filterItems(name = "") {
  if (!name) {
    displayItems(items);
    return;
  }
  const regex = new RegExp(`\\b${name}\\b`, "i");
  const filteredItems = items.filter((item) => regex.test(item.name));
  displayItems(filteredItems);
}

function displayItems(items) {
  const itemsBody = document.getElementById("items-body");
  itemsBody.innerHTML = "";
  items.forEach((item) => {
    const itemDiv = document.createElement("div");
    itemDiv.className = "col mb-4";
    itemDiv.innerHTML = `
      <div class="card">
        <img class="card-img-top" src="${item.img}" alt="Card image" />
        <div class="d-flex justify-content-between card-body">
          <h5 class="card-title">${item.name}</h5>
          <h5 class="card-title">$${item.price}</h5>
        </div>
      </div>
    `;
    itemsBody.appendChild(itemDiv);
  });
}
displayItems(items);
