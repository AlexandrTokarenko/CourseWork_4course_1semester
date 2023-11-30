function validateInputPositiveInteger(input) {
    document.getElementById(input).addEventListener('input', function(event) {
        const input = event.target;
        input.value = input.value.replace(/\D/g, '');
        if (input.value.length > 0 && input.value[0] === '0') {
            input.value = input.value.slice(1);
        }
    });
}

function validateInputPositiveFloat(inputId) {
    const inputElement = document.getElementById(inputId);

    if (!inputElement) {
        console.error(`Element with id '${inputId}' not found.`);
        return;
    }

    inputElement.addEventListener('input', function(event) {
        const input = event.target;
        input.value = input.value.replace(/[^0-9.]/g, '');
        const parts = input.value.split('.');
        if (parts.length > 2) {
            input.value = parts[0] + '.' + parts.slice(1).join('');
        }
        if (input.value.length > 0 && input.value[0] === '.') {
            input.value = '0' + input.value;
        }
    });
}
