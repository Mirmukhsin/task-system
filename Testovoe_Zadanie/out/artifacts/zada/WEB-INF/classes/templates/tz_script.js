const statuslar = document.getElementsByName('status');
const sValue = document.getElementById('sValue').value;
statuslar.forEach(status => {
    if (status.value === sValue) {
        status.checked = true;
    }
});
const prioritylar = document.getElementsByName('priority');
const pValue = document.getElementById('pValue').value;
prioritylar.forEach(priority => {
    if (priority.value === pValue) {
        priority.checked = true;
    }
});