const toggleBtn = document.querySelector('.nav__toggleBtn');
const menu = document.querySelector('.nav__menu');
const account = document.querySelector('.nav__account');

toggleBtn.addEventListener('click', () => {
    menu.classList.toggle('active');
    account.classList.toggle('active');
    
})
