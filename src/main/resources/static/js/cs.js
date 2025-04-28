/*Simpler variable names (student, response, etc.)
Clear try-catch blocks
fetch error handling
Clear comments for beginners
Cleaner logic structure

Debounce the search input (search after user stops typing for 300ms instead of every letter).
Always catch errors while doing fetch or await.
Handle empty fields and validate before submitting.


*/


// Base API URL for student-related actions
const API = "student/test";

// When the page finishes loading
document.addEventListener("DOMContentLoaded", () => {
  loadStudents(); // Load students initially

  // Attach event listener to the student form submit event
  document.getElementById("studentForm").addEventListener("submit", saveStudent);

  // Attach event listener for the search input (filter students while typing)
  document.getElementById("searchInput").addEventListener("input", () => {
    const searchValue = document.getElementById("searchInput").value;
    loadStudents(searchValue); // Reload students based on search text
  });
});

// Function to load and display students
function loadStudents(search = "") {
  fetch(`${API}?search=${encodeURIComponent(search)}`) // Call API with optional search term
    .then(response => response.json()) // Parse the response as JSON
    .then(data => {
      const tbody = document.getElementById("studentTableBody"); // Get table body
      tbody.innerHTML = ""; // Clear previous data

      // Loop through each student and add a row to the table
      data.forEach(student => {
        const row = document.createElement("tr");
        row.innerHTML = `
          <td>${student.firstName} ${student.middleName || ""} ${student.lastName}</td>
          <td>${student.dateOfBirth}</td>
          <td>${student.gender}</td>
          <td>${student.registrationDate}</td>
          <td>${student.phone}</td>
          <td>${student.kifleKetema} ${student.kebele}</td>
          <td>${student.isActive ? "Active" : "Inactive"}</td>
          <td>
            <button onclick='editStudent(${JSON.stringify(student)})'>Edit</button>
            <button onclick='deleteStudent(${student.studentId})'>Delete</button>
          </td>
        `;
        tbody.appendChild(row); // Add the row to the table
      });
    })
    .catch(error => {
      console.error("Error loading students:", error);
      alert("Failed to load students.");
    });
}

// Function to save a new student or update an existing one
async function saveStudent(event) {
  event.preventDefault(); // Prevent form from submitting normally

  // Create a student object from form fields
  const studentId = document.getElementById("studentId").value;
  const student = {
    studentId: studentId ? Number(studentId) : undefined,
    firstName: document.getElementById('firstName').value,
    middleName: document.getElementById('middleName').value,
    lastName: document.getElementById('lastName').value,
    dateOfBirth: document.getElementById('dob').value,
    gender: document.getElementById('gender').value,
    registrationDate: document.getElementById('regDate').value,
    photo: document.getElementById('photo')?.value || "",
    kifleKetema: document.getElementById('kifleKetema').value,
    kebele: document.getElementById('kebele').value,
    houseNumber: document.getElementById('houseNumber').value,
    phone: document.getElementById('phone').value,
    comment: document.getElementById('comment').value,
    isActive: document.getElementById('isActive').checked
    document.getElementById("searchInput").value = ""; // Clear search box
    loadStudents(); // Reload students

  };

  try {
    const method = studentId ? "PUT" : "POST"; // If we have an ID, it's an update
    const url = studentId ? `${API}/${studentId}` : `${API}/save`; // Choose correct endpoint

    const response = await fetch(url, {
      method: method,
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(student)
    });

    if (response.ok) {
      alert("Student saved successfully!");
      clearForm();
      loadStudents();
      // Hide modal if open
      bootstrap.Modal.getInstance(document.getElementById("studentModal"))?.hide();
    } else {
      const errorData = await response.json();
      alert("Error: " + (errorData.message || "Unknown error while saving."));
    }
  } catch (error) {
    console.error("Error saving student:", error);
    alert("Something went wrong while saving.");
  }
}

// Function to edit an existing student
function editStudent(student) {
  for (const key in student) {
    const field = document.getElementById(key);
    if (field) {
      if (field.type === "checkbox") {
        field.checked = student[key]; // Check or uncheck checkbox
      } else if (field.type !== "file") {
        field.value = student[key]; // Fill field with value
      }
    }
  }

  document.getElementById("searchInput").value = ""; // Clear search box
  loadStudents(); // Reload students
  document.getElementById("studentModalLabel").textContent = "Edit Student"; // Change modal title
  new bootstrap.Modal(document.getElementById("studentModal")).show(); // Show modal
}

// Function to delete a student
function deleteStudent(studentId) {
  if (confirm("Are you sure you want to delete this student?")) {
    fetch(`${API}/${studentId}`, { method: "DELETE" })
      .then(response => {
        if (response.ok) {
          alert("Student deleted successfully!");
          document.getElementById("searchInput").value = "";
          loadStudents();
        } else {
          alert("Failed to delete student.");
        }
      })
      .catch(error => {
        console.error("Error deleting student:", error);
      });
  }
}

// Function to clear the form
function clearForm() {
  document.getElementById("studentForm").reset(); // Clear form fields
  document.getElementById("studentId").value = ""; // Clear hidden ID field
  document.getElementById("photoPreview").style.display = "none"; // Hide photo preview
}

// Function to open modal for adding a new student
function openAddModal() {
  resetForm();
  document.getElementById("studentModalLabel").textContent = "Add Student";
  new bootstrap.Modal(document.getElementById("studentModal")).show();
}

// Function to reset form (used when adding a new student)
function resetForm() {
  document.getElementById("studentForm").reset();
  document.getElementById("studentId").value = "";
  document.getElementById("photoPreview").style.display = "none";
}

