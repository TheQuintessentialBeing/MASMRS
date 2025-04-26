            const API = "student/test";
            debugger;
            document.addEventListener("DOMContentLoaded", () => {
            debugger;
              loadStudents();
              document.getElementById("studentForm").addEventListener("submit", handleSubmit);
              document.getElementById("searchInput").addEventListener("input", () => {
                loadStudents(document.getElementById("searchInput").value);
              });
            });

                   document.getElementById('studentForm').addEventListener('submit', async function(event) {
                            event.preventDefault(); // prevent default form submit behavior
                                const student = {
                                studentId : 1111,
                                firstName: document.getElementById('firstName').value,
                                middleName: document.getElementById('middleName').value,
                                lastName: document.getElementById('lastName').value,
                                dateOfBirth: document.getElementById('dob').value,
                                gender: document.getElementById('gender').value,
                                registrationDate: document.getElementById('regDate').value,
                                photo: document.getElementById('gender').value,
                                kifleKetema: document.getElementById('kifleKetema').value,
                                kebele: document.getElementById('kebele').value,
                                houseNumber: document.getElementById('houseNumber').value,
                                phone: document.getElementById('phone').value,
                                comment: document.getElementById('comment').value,
                                isActive: document.getElementById('isActive').value
                               };

                            try {
                                const response = await fetch('student/test/save', {
                                    method: 'POST',
                                    headers: {
                                        'Content-Type': 'application/json'
                                    },
                                    body: JSON.stringify(student)
                                });

                                if (response.ok) {
                                    alert('Student saved successfully!');
                                    document.getElementById('studentForm').reset(); // clear the form
                                    // optionally close the modal here if needed
                                    location.reload(); // or refresh the table data dynamically
                                } else {
                                    const errorData = await response.json();
                                    alert('Error: ' + errorData.message || 'Failed to save student.');
                                }
                            } catch (error) {
                                console.error('Error:', error);
                                alert('Something went wrong while saving.');
                            }
                        });


            function loadStudents(search = "") {
            debugger;
              fetch(`${API}?search=${encodeURIComponent(search)}`)
               .then(res => res.json())
               .then(data => {
                  const tbody = document.getElementById("studentTableBody");
                  tbody.innerHTML = "";
                  console.log("data:" + data)
                  data.forEach(s => {
                    const row = document.createElement("tr");
                    row.innerHTML = `

                      <td>${s.firstName} ${s.middleName || ""} ${s.lastName}</td>
                      <td>${s.dateOfBirth}</td>
                      <td>${s.gender}</td>
                      <td>${s.registrationDate}</td>
                        <td>${s.phone}</td>
                      <td>${s.kifleKetema} ${s.kebele} </td>
                      <td>${s.isActive}</td>
                     <td>
                     <span>
                        <button onclick='editStudent(${JSON.stringify(s)})'>Edit</button> </span>
                        <span>
                        <button onclick='deleteStudent(${s.studentId})'>Delete</button> </span>
                      </td>
                    `;
                    tbody.appendChild(row);
                  });
                });
            }
            function handleSubmit(e) {
              e.preventDefault();
              const student = {
              /*// studentId: document.getElementById("studentId").value || null,*/
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
            debugger;
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
            function saveStudent(Student){


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
            // Open modal for Add
            function openAddModal() {
              resetForm();
              document.getElementById("studentModalLabel").textContent = "Add Student";
              new bootstrap.Modal(document.getElementById("studentModal")).show();
            }
            // Reset form
            function resetForm() {
              studentForm.reset();
              document.getElementById("studentId").value = "";
              document.getElementById("photoPreview").style.display = "none";
            }


