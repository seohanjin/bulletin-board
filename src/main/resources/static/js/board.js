const reComment = document.querySelector('.write_ReComment');
const comment_tag = document.querySelector('.header_right_comment');

// comment_tag.addEventListener('click', () => {
//     document.querySelector('.write_ReComment').style.display = 'block';
//     document.querySelector('.write_comment_form').setAttribute("action", "/board/api/v1");
// });

// const close_comment_box = document.querySelector('.header_info');

// close_comment_box.addEventListener('click', () => {
//     document.querySelector('.write_ReComment').style.display = 'none';
// })

const re_comment = document.querySelector('#header_info');
const ch_back_co = document.querySelector('#write_comment_wrap');
const close_1 = document.querySelector('#close_button');
const comment_submit_btn = document.querySelector('#button_border');
const re_comment_submit_btn = document.querySelector('#re_comment_span');

function comment() {
    re_comment.innerHTML = '대댓글 쓰기';
    ch_back_co.style.backgroundColor = '#f5f8ff';
    ch_back_co.scrollIntoView(true);
    close_1.style.display = 'block';
    comment_submit_btn.style.display = 'none';
    re_comment_submit_btn.style.display = 'block';
}

function re_comment_cancel() {
    re_comment.innerHTML = '댓글 쓰기';
    ch_back_co.style.backgroundColor = 'rgb(245, 245, 245)';
    close_1.style.display = 'none';
    comment_submit_btn.style.display = 'block';
    re_comment_submit_btn.style.display = 'none';
}

