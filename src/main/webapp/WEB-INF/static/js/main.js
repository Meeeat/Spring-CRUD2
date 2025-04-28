document.addEventListener('DOMContentLoaded', function() {
    // Auto-close alerts after 5 seconds
    const alertElements = document.querySelectorAll('.alert');
    alertElements.forEach(function(alert) {
        setTimeout(function() {
            const closeButton = alert.querySelector('.btn-close');
            if (closeButton) {
                closeButton.click();
            }
        }, 5000);
    });

    // Confirm delete operations
    const deleteButtons = document.querySelectorAll('.btn-danger[href*="/delete/"]');
    deleteButtons.forEach(function(button) {
        button.addEventListener('click', function(event) {
            if (!confirm('Are you sure you want to delete this task?')) {
                event.preventDefault();
            }
        });
    });

    // Highlight current page in pagination
    const currentPageItem = document.querySelector('.page-item.active');
    if (currentPageItem) {
        currentPageItem.classList.add('fw-bold');
    }

    // Apply task status highlighting
    const taskRows = document.querySelectorAll('[class*="task-status-"]');
    taskRows.forEach(function(row) {
        const status = row.querySelector('td:nth-child(3)').textContent;
        const statusCell = row.querySelector('td:nth-child(3)');

        let badgeClass = 'bg-warning';
        if (status === 'DONE') {
            badgeClass = 'bg-success';
        } else if (status === 'PAUSED') {
            badgeClass = 'bg-danger';
        }

        statusCell.innerHTML = `<span class="badge ${badgeClass}">${status}</span>`;
    });
});