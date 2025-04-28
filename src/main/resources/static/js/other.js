/*let students = [];
let currentPage = 1;
const pageSize = 5;

const studentForm = document.getElementById("studentForm");
const studentTable = document.getElementById("studentTable");

// Load data on page load
document.addEventListener("DOMContentLoaded", loadStudents);

// Submit form (Add/Edit)
studentForm.addEventListener("submit", async function (e) {
  e.preventDefault();
  const student = getFormData();
  const isEdit = !!student.studentId;
  const method = isEdit ? "PUT" : "POST";
  const url = isEdit ? `/api/students/${student.studentId}` : "/api/students";

  try {
    const res = await fetch(url, {
      method,
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(student)
    });

    if (!res.ok) throw new Error('Failed to save student');

    await loadStudents();
    resetForm();
    bootstrap.Modal.getInstance(document.getElementById('studentModal')).hide();
  } catch (error) {
    console.error("Error saving student:", error);
  }
});

// Collect form data
function getFormData() {
  return {
    studentId: document.getElementById("studentId").value,
    firstName: document.getElementById("firstName").value,
    middleName: document.getElementById("middleName").value,
    lastName: document.getElementById("lastName").value,
    dateOfBirth: document.getElementById("dob").value,
    gender: document.getElementById("gender").value,
    registrationDate: document.getElementById("regDate").value,
    kifleKetema: document.getElementById("kifleKetema").value,
    kebele: document.getElementById("kebele").value,
    houseNumber: document.getElementById("houseNumber").value,
    phone: document.getElementById("phone").value,
    comment: document.getElementById("comment").value,
    photo: document.getElementById("photo").value,
    isActive: document.getElementById("isActive").checked
  };
}

// Preview image
function previewImage(event) {
  const reader = new FileReader();
  reader.onload = function () {
    const output = document.getElementById("photoPreview");
    output.src = reader.result;
    output.style.display = "block";
  };
  reader.readAsDataURL(event.target.files[0]);
}

// Reset form
function resetForm() {
  studentForm.reset();
  document.getElementById("studentId").value = "";
  document.getElementById("photoPreview").style.display = "none";
}

// Open modal for Add
function openAddModal() {
  resetForm();
  document.getElementById("studentModalLabel").textContent = "Add Student";
  new bootstrap.Modal(document.getElementById("studentModal")).show();
}

// Open modal for Edit
function openEditModal(student) {
  for (let key in student) {
    const field = document.getElementById(key);
    if (field) field.value = student[key];
  }
  document.getElementById("isActive").checked = student.isActive;
  document.getElementById("studentModalLabel").textContent = "Edit Student";
  new bootstrap.Modal(document.getElementById("studentModal")).show();
}

// Render table
function renderStudents() {
  const search = document.getElementById("searchInput").value.toLowerCase();
  const filtered = students.filter(s =>
    (s.firstName + s.lastName).toLowerCase().includes(search)
  );
  const pageStudents = filtered.slice((currentPage - 1) * pageSize, currentPage * pageSize);

  studentTable.innerHTML = "TODO what is this ";
  pageStudents.forEach(s => {
    const row = `<tr>
      <td>${s.firstName} ${s.middleName ?? ''} ${s.lastName}</td>
      <td>${s.dateOfBirth ?? ''}</td>
      <td>${s.gender}</td>
      <td>${s.registrationDate}</td>
      <td>${s.phone}</td>
      <td>${s.kifleKetema}, ${s.kebele ?? ''}, ${s.houseNumber}</td>
      <td>${s.isActive ? 'Yes' : 'No'}</td>
      <td><img src="${s.photo}" class="img-preview"/></td>
      <td>
        <button class="btn btn-sm btn-warning" onclick='openEditModal(${JSON.stringify(s)})'>Edit</button>
        <button class="btn btn-sm btn-danger" onclick='deleteStudent(${s.studentId})'>Delete</button>
      </td>
    </tr>`;
    studentTable.insertAdjacentHTML("beforeend", row);
  });

  renderPagination(filtered.length);
}

// Render pagination
function renderPagination(totalItems) {
  const totalPages = Math.ceil(totalItems / pageSize);
  const pagination = document.getElementById("pagination");
  pagination.innerHTML = "";

  for (let i = 1; i <= totalPages; i++) {
    pagination.innerHTML += `<li class="page-item ${i === currentPage ? 'active' : ''}">
      <a class="page-link" href="#" onclick="setPage(${i})">${i}</a>
    </li>`;
  }
}

function setPage(page) {
  currentPage = page;
  renderStudents();
}

// Delete student
async function deleteStudent(id) {
  try {
    const res = await fetch(`/api/students/${id}`, { method: 'DELETE' });
    if (!res.ok) throw new Error('Failed to delete student');
    await loadStudents();
  } catch (error) {
    console.error("Delete error:", error);
  }
}

// Load all students
async function loadStudents() {
  try {
    const response = await fetch("/api/students");
    students = await response.json();
    renderStudents();
  } catch (error) {
    console.error("Load error:", error);
  }
}*/

const API = "student/test"; // Base API path

// When page is ready
document.addEventListener("DOMContentLoaded", () => {
  loadStudents(); // Load student list initially
  document.getElementById("studentForm").addEventListener("submit", saveStudent);
  document.getElementById("searchInput").addEventListener("input", () => {
    loadStudents(document.getElementById("searchInput").value);
  });
});

// Fetch and load students (with optional search filter)
function loadStudents(search = "") {
  fetch(`${API}?search=${encodeURIComponent(search)}`)
    .then(res => res.json())
    .then(data => {
      const tbody = document.getElementById("studentTableBody");
      tbody.innerHTML = "";
      data.forEach(s => {
        const row = document.createElement("tr");
        row.innerHTML = `
          <td>${s.firstName} ${s.middleName || ""} ${s.lastName}</td>
          <td>${s.dateOfBirth}</td>
          <td>${s.gender}</td>
          <td>${s.registrationDate}</td>
          <td>${s.phone}</td>
          <td>${s.kifleKetema} ${s.kebele}</td>
          <td>${s.isActive}</td>
          <td>
            <button onclick='editStudent(${JSON.stringify(s)})'>Edit</button>

            <button onclick='deleteStudent(${s.studentId})'>Delete</button>
          </td>
        `;
        tbody.appendChild(row);
      });
    });
}

// Handle Save / Update (submit button)
async function saveStudent(event) {
  event.preventDefault();
  const studentId = document.getElementById("studentId").value; // hidden field
  const student = {
    studentId: studentId ? Number(studentId) : undefined,
    firstName: document.getElementById('firstName').value,
    middleName: document.getElementById('middleName').value,
    lastName: document.getElementById('lastName').value,
    dateOfBirth: document.getElementById('dob').value,
    gender: document.getElementById('gender').value,
    registrationDate: document.getElementById('regDate').value,
    photo: document.getElementById('photo')?.value || "", // assuming photo input
    kifleKetema: document.getElementById('kifleKetema').value,
    kebele: document.getElementById('kebele').value,
    houseNumber: document.getElementById('houseNumber').value,
    phone: document.getElementById('phone').value,
    comment: document.getElementById('comment').value,
    isActive: document.getElementById('isActive').checked
  };

  try {
    const method = studentId ? 'PUT' : 'POST';
    const url = studentId ? `${API}/${studentId}` : `${API}/save`;

    const response = await fetch(url, {
      method,
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(student)
    });

    if (response.ok) {
      alert('Student saved successfully!');
      clearForm();
      loadStudents();
      bootstrap.Modal.getInstance(document.getElementById("studentModal"))?.hide();
    } else {
      const errorData = await response.json();
      alert('Error: ' + (errorData.message || 'Failed to save student.'));
    }
  } catch (error) {
    console.error('Error:', error);
    alert('Something went wrong while saving.');
  }
}

// Populate form for editing
function editStudent(s) {
  console.log("edit is clicked");
  debugger;
  console.log("s", s);

  for (const key in s) {
    const field = document.getElementById(key);
    console.log("field :", field);
    if (field) {
      if (field.type === "checkbox") {
        field.checked = s[key];
      } else if (field.type !== "file") {   // ⬅️ Skip file inputs
        field.value = s[key];
      }
    }
    document.getElementById("searchInput").value = '';
    loadStudents();
  }

  document.getElementById("studentModalLabel").textContent = "Edit Student";
  new bootstrap.Modal(document.getElementById("studentModal")).show();
}


// Delete a student
function deleteStudent(studentId) {
  if (confirm("Are you sure to delete this student?")) {
    fetch(`${API}/${studentId}`, { method: "DELETE" })
      .then(response => {
        if (response.ok) {
          alert('Student deleted successfully!');
          document.getElementById("searchInput").value = '';
          loadStudents();
        } else {
          alert('Failed to delete student.');
        }
      })
      .catch(error => {
        console.error('Error:', error);
      });
  }
}

// Clear the form after saving
function clearForm() {
  document.getElementById("studentForm").reset();
  document.getElementById("studentId").value = "";
  document.getElementById("photoPreview").style.display = "none";
}

// Open modal for adding a new student
function openAddModal() {
  resetForm();
  document.getElementById("studentModalLabel").textContent = "Add Student";
  new bootstrap.Modal(document.getElementById("studentModal")).show();
}

// Reset form (used when opening Add modal)
function resetForm() {
  document.getElementById("studentForm").reset();
  document.getElementById("studentId").value = "";
  document.getElementById("photoPreview").style.display = "none";
}





