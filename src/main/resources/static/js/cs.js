<script>
        const API = "/api/students";
    
        document.addEventListener("DOMContentLoaded", () => {
          loadStudents();
          document.getElementById("studentForm").addEventListener("submit", handleSubmit);
          document.getElementById("searchInput").addEventListener("input", () => {
            loadStudents(document.getElementById("searchInput").value);
          });
        });
    
        function loadStudents(search = "") {
          fetch(`${API}?search=${encodeURIComponent(search)}`)
            .then(res => res.json())
            .then(data => {
              const tbody = document.getElementById("studentTableBody");
              tbody.innerHTML = "";
              data.forEach(s => {
                const row = document.createElement("tr");
                row.innerHTML = `
                  <td>${s.studentId}</td>
                  <td>${s.firstName} ${s.middleName || ""} ${s.lastName}</td>
                  <td>${s.gender}</td>
                  <td>${s.phone}</td>
                  <td>
                    <button onclick='editStudent(${JSON.stringify(s)})'>Edit</button>
                    <button onclick='deleteStudent(${s.studentId})'>Delete</button>
                  </td>
                `;
                tbody.appendChild(row);
              });
            });
        }
    
        function handleSubmit(e) {
          e.preventDefault();
          const student = {
            studentId: document.getElementById("studentId").value || null,
            firstName: document.getElementById("firstName").value,
            middleName: document.getElementById("middleName").value,
            lastName: document.getElementById("lastName").value,
            dateOfBirth: document.getElementById("dateOfBirth").value,
            gender: document.getElementById("gender").value,
            registrationDate: document.getElementById("registrationDate").value,
            kifleKetema: document.getElementById("kifleKetema").value,
            kebele: document.getElementById("kebele").value,
            houseNumber: document.getElementById("houseNumber").value,
            phone: document.getElementById("phone").value,
            comment: document.getElementById("comment").value,
            isActive: document.getElementById("isActive").checked
          };
    
          const method = student.studentId ? "PUT" : "POST";
          const url = student.studentId ? `${API}/${student.studentId}` : API;
    
          fetch(url, {
            method,
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(student)
          }).then(() => {
            clearForm();
            loadStudents();
          });
        }
    
        function editStudent(s) {
          for (const key in s) {
            const field = document.getElementById(key);
            if (field) {
              if (field.type === "checkbox") {
                field.checked = s[key];
              } else {
                field.value = s[key];
              }
            }
          }
        }
    
        function deleteStudent(id) {
          if (confirm("Are you sure to delete this student?")) {
            fetch(`${API}/${id}`, { method: "DELETE" })
              .then(() => loadStudents());
          }
        }
    
        function clearForm() {
          document.getElementById("studentForm").reset();
          document.getElementById("studentId").value = "";
        }
    </script>