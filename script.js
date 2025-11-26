const descInput = document.getElementById("desc");
const amountInput = document.getElementById("amount");
const categorySelect = document.getElementById("category");
const addBtn = document.getElementById("addBtn");
const clearBtn = document.getElementById("clearBtn");
const tableBody = document.getElementById("expenseTableBody");
const totalSpan = document.getElementById("total");

let expenses = [];

window.onload = function () {
  const saved = localStorage.getItem("expenses");
  if (saved) {
    expenses = JSON.parse(saved);
    refresh();
  }
};

addBtn.onclick = function () {
  const desc = descInput.value.trim();
  const amount = parseFloat(amountInput.value);

  if (!desc || !amount || amount <= 0) {
    alert("Enter valid details");
    return;
  }

  const category = categorySelect.value;

  const exp = {
    id: Date.now(),
    desc,
    amount,
    category
  };

  expenses.push(exp);
  saveData();
  refresh();

  descInput.value = "";
  amountInput.value = "";
};

clearBtn.onclick = function () {
  if (confirm("Clear all?")) {
    expenses = [];
    saveData();
    refresh();
  }
};

function deleteExpense(id) {
  expenses = expenses.filter(e => e.id !== id);
  saveData();
  refresh();
}

function refresh() {
  tableBody.innerHTML = "";

  expenses.forEach(exp => {
    const row = document.createElement("tr");

    row.innerHTML = `
      <td>${exp.desc}</td>
      <td>₹${exp.amount}</td>
      <td>${exp.category}</td>
      <td><button class="delete-btn" onclick="deleteExpense(${exp.id})">Delete</button></td>
    `;

    tableBody.appendChild(row);
  });

  updateTotal();
}

function updateTotal() {
  const total = expenses.reduce((sum, e) => sum + e.amount, 0);
  totalSpan.textContent = total.toFixed(2);
}

function saveData() {
  localStorage.setItem("expenses", JSON.stringify(expenses));
}

