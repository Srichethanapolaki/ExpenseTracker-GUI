const descInput = document.getElementById("desc");
const amountInput = document.getElementById("amount");
const categorySelect = document.getElementById("category");
const addBtn = document.getElementById("addBtn");
const clearBtn = document.getElementById("clearBtn");
const tableBody = document.getElementById("expenseTableBody");
const totalSpan = document.getElementById("total");

let expenses = [];

// Load from localStorage if present
window.onload = function () {
  const saved = localStorage.getItem("expenses");
  if (saved) {
    expenses = JSON.parse(saved);
    renderTable();
    updateTotal();
  }
};

addBtn.addEventListener("click", () => {
  const desc = descInput.value.trim();
  const amount = parseFloat(amountInput.value);
  const category = categorySelect.value;

  if (!desc || isNaN(amount) || amount <= 0) {
    alert("Please enter a valid description and amount.");
    return;
  }

  const expense = {
    id: Date.now(),
    desc,
    amount,
    category,
  };

  expenses.push(expense);
  saveAndRefresh();

  // clear inputs
  descInput.value = "";
  amountInput.value = "";
  categorySelect.value = "Food";
});

clearBtn.addEventListener("click", () => {
  if (confirm("Clear all expenses?")) {
    expenses = [];
    saveAndRefresh();
  }
});

function saveAndRefresh() {
  localStorage.setItem("expenses", JSON.stringify(expenses));
  renderTable();
  updateTotal();
}

function renderTable() {
  tableBody.innerHTML = "";
  expenses.forEach((exp) => {
    const tr = document.createElement("tr");

    tr.innerHTML = `
      <td>${exp.desc}</td>
      <td>₹${exp.amount}</td>
      <td>${exp.category}</td>
      <td><button class="action-btn" onclick="deleteExpense(${exp.id})">Delete</button></td>
    `;

    tableBody.appendChild(tr);
  });
}

function updateTotal() {
  const total = expenses.reduce((sum, exp) => sum + exp.amount, 0);
  totalSpan.textContent = total.toFixed(2);
}

function deleteExpense(id) {
  expenses = expenses.filter((e) => e.id !== id);
  saveAndRefresh();
}
