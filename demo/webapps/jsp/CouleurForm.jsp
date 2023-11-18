<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Couleur</title>
    <!-- Inclure les fichiers CSS/JS si nécessaire -->
    <!-- http://localhost:8080/demo/webapps/jsp/CouleurForm.jsp -->
</head>
<body>
    <h2>Create Couleur Form</h2>
    <form id="createCouleurForm" action="/couleur" method="post">
        <label for="nom">Nom de la Couleur:</label>
        <input type="text" id="nom" name="nom" required><br><br>
        
        <label for="hexadecimal_rvb">Code Hexadecimal RGB:</label>
        <input type="text" id="hexadecimal_rvb" name="hexadecimal_rvb" required><br><br>
        
        <input type="submit" value="Submit">
    </form>

    <script>
        document.getElementById('createCouleurForm').addEventListener('submit', function(event) {
            event.preventDefault();

            var formData = new FormData(this);
            var jsonData = {};

            for (var [key, value] of formData.entries()) {
                jsonData[key] = value;
            }

            console.log(jsonData);

            fetch('http://localhost:8080/couleur', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(jsonData)
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok: ' + response.statusText);
                }
                return response.json();
            })
            .then(data => {
                // Traiter la réponse (par exemple, afficher un message de succès)
                console.log(data);
            })
            .catch(error => {
                // Gérer les erreurs ici
                console.error('Error:', error);
            });
        });
    </script>
</body>
</html>
