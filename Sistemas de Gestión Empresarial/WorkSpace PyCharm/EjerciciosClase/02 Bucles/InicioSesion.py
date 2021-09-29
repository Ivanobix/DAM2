contrasenaPredefinida = "admin";
contrasenaIntroducida = "";
logueado = False;

for intento in range(3):
    contrasenaIntroducida = input("Introduce la contraseña: ");
    if contrasenaPredefinida == contrasenaIntroducida:
        logueado = True;
        break;
if logueado:
    print("Sesión iniciada");
else:
    print("Inicio de sesión fallido");
