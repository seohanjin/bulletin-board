// ---------- Same Password Inspection ---------------
const password = document.getElementById('password');
const password_confirm = document.getElementById('password_confirm');
const alert_password = document.querySelector('.password_equalerror');
const target = document.querySelector('.signup__btn');

password_confirm.addEventListener('focusout', () => {
    if (password.value != password_confirm.value) {
      alert_password.style.display = 'block';
    }else {
      alert_password.style.display = 'none';
      target.disabled = false;
    }
})