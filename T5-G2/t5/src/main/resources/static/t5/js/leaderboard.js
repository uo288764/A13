let currentPage = 1;
let rowsPerPage = 10;

const fetchLeaderboardData = async () => {
    try {
        const response = await axios.get('http://localhost:8080/api/user/getallResult');
        console.log(response.data); // Verifica los datos
        return response.data;
    } catch (error) {
        console.error('Error fetching leaderboard data:', error);
        return [];
    }
};

const renderLeaderboard = (paginatedData) => {
    const leaderboardContainer = document.getElementById('leaderboardBody');
    leaderboardContainer.innerHTML = ''; // Limpiar el contenido actual
    
    if (paginatedData.length === 0) {
        leaderboardContainer.innerHTML = '<tr><td colspan="5">No data available</td></tr>';
    } else {
        paginatedData.forEach(user => {
            const userRow = document.createElement('tr');
            userRow.innerHTML = `
                <td>${user.rank}</td>
                <td>${user.email}</td>
                <td>${user.gamesWon}</td>
                <td>${user.gamesPlayed}</td>
                <td>${user.gamesRate}%</td>
            `;
            leaderboardContainer.appendChild(userRow);
        });
    }
};

const paginateData = (data) => {
    const startIndex = (currentPage - 1) * rowsPerPage;
    const endIndex = startIndex + rowsPerPage;
    return data.slice(startIndex, endIndex); // Paginación correcta
};

const updatePaginationControls = (totalRows) => {
    const totalPages = Math.ceil(totalRows / rowsPerPage);
    const paginationContainer = document.getElementById('paginationControls');
    paginationContainer.innerHTML = ''; // Limpiar los controles de paginación

    for (let i = 1; i <= totalPages; i++) {
        const pageButton = document.createElement('button');
        pageButton.textContent = i;
        pageButton.classList.add('btn', 'btn-sm', 'btn-secondary', 'mx-1');
        if (i === currentPage) {
            pageButton.classList.add('active');
        }
        pageButton.addEventListener('click', () => {
            currentPage = i;
            displayLeaderboard(); // Actualizar la página con los datos correctos
        });
        paginationContainer.appendChild(pageButton);
    }
};

const displayLeaderboard = async () => {
    const leaderboardData = await fetchLeaderboardData();
    const paginatedData = paginateData(leaderboardData); // Aplicar paginación
    renderLeaderboard(paginatedData);
    updatePaginationControls(leaderboardData.length); // Mostrar los controles de paginación
};

document.getElementById('rowsPerPageSelect').addEventListener('change', (event) => {
    rowsPerPage = parseInt(event.target.value, 10); // Cambiar el número de filas por página
    currentPage = 1; // Volver a la primera página
    displayLeaderboard(); // Recargar los datos
});

document.getElementById('searchEmail').addEventListener('keyup', function () {
    const searchText = this.value.toLowerCase();
    const rows = document.getElementById('leaderboardBody').getElementsByTagName('tr');

    for (let i = 0; i < rows.length; i++) {
        const emailCell = rows[i].getElementsByTagName('td')[1];
        if (emailCell) {
            const emailText = emailCell.textContent.toLowerCase();
            rows[i].style.display = emailText.includes(searchText) ? '' : 'none';
        }
    }
});

document.addEventListener('DOMContentLoaded', displayLeaderboard);
