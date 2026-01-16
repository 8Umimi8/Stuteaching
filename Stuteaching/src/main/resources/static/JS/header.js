
document.addEventListener("DOMContentLoaded", () => {
    const hamburger = document.querySelector('.hamburger');
    const navMenu = document.querySelector('.nav-menu');

    if (hamburger && navMenu) {
        hamburger.addEventListener('click', () => {
            navMenu.classList.toggle('active');
        });
    }
});

const notifyBtn = document.getElementById("notifyBtn");
const notificationPanel = document.getElementById("notificationPanel");

if (notifyBtn && notificationPanel) {
    notifyBtn.addEventListener("click", (e) => {
        e.preventDefault();
        notificationPanel.style.display =
            notificationPanel.style.display === "block" ? "none" : "block";
    });
}
