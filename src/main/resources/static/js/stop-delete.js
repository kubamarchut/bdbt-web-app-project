const deleteBTNs = document.querySelectorAll('td.actions a:nth-of-type(2)');
const modal = document.querySelector('#deletePrevention');
const confBtn = modal.querySelector('.btn-danger');

deleteBTNs.forEach((btn) =>{
    btn.addEventListener('click', (e)=>{
        e.preventDefault();
        let href = e.target.parentElement.href;
        $('#deletePrevention').modal('show');
        confBtn.href = href
        confBtn.addEventListener('click', (e)=>{
            window.location.href = e.currentTarget.href;
        }, false)
    })
})