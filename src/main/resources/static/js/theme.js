// ...existing code...

console.log("yes...........")

console.log("this is profile page");
function exportData() {
  TableToExcel.convert(document.getElementById("contact-table"), {
    name: "contacts.xlsx",
    sheet: {
      name: "Sheet 1",
    },
  });
}
document.addEventListener("DOMContentLoaded", function () {
  const themeToggleBtn = document.getElementById("theme-toggle");
  const html = document.documentElement;

  function setTheme(mode) {
    if (mode === "dark") {
      html.classList.add("dark");
      themeToggleBtn.innerHTML = `<i class="fa-regular fa-sun"></i> Dark`;
    } else {
      html.classList.remove("dark");
      themeToggleBtn.innerHTML = `<i class="fa-regular fa-moon"></i> Light`;
    }
    localStorage.setItem("theme", mode);
  }

  // Load theme from localStorage
  const savedTheme = localStorage.getItem("theme") || "light";
  setTheme(savedTheme);

  themeToggleBtn.addEventListener("click", function () {
    const isDark = html.classList.contains("dark");
    setTheme(isDark ? "light" : "dark");
  });
});

// ...existing code...