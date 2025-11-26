# 📊 Personal Expense Tracker — Java Swing GUI

A simple and elegant GUI application to track daily expenses with category-based reporting.  
Built using **Core Java + Swing**, ideal for students, beginners, and resume projects.

---

## 🚀 Features

- Add expense with category (Food, Travel, Shopping, Bills, Others)
- Record amount easily
- Automatic total expense calculation
- Category-wise spending breakdown
- Clean and user-friendly interface
- Uses ArrayList (no database needed)
- 100% Java-based and portable

---

## 🛠️ Tech Stack

- **Java (JDK 17 or above)**
- **Java Swing**
- **OOP Concepts**
- **ArrayList**

---

## 📁 Project Structure

```
ExpenseTracker-GUI/
 ├── src/
 │    └── ExpenseTracker.java
 ├── screenshots/
 └── README.md
```

---

## 🔧 How to Run

### Compile:
```
javac src/ExpenseTracker.java
```

### Run:
```
java src.ExpenseTracker
```

---

## 📐 UML Diagram

```
Expense
 ├─ category : String
 └─ amount : double

ExpenseTracker
 ├─ expenses : ArrayList<Expense>
 ├─ addExpense()
 ├─ showReport()
 └─ GUI Components
```

---

## 🎓 Viva Questions

**Q1: What concepts did you use?**  
OOP, ArrayList, Swing GUI, Event Handling.

**Q2: Why Swing?**  
It's lightweight, easy to implement, and works everywhere.

**Q3: Future improvements?**  
Add graphs, file storage, monthly summaries, login system.

---

## 📝 Resume Description

**Personal Expense Tracker (Java GUI):**  
Designed a GUI-based expense management tool using Java Swing.  
Users can add expenses, categorize them, and generate a spending report.  
Implemented using OOP, event-driven programming, and ArrayList structures.
