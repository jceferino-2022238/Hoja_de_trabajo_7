# Diccionario Francés–Español con BST

## Uso

Ejecuta el programa desde la carpeta del proyecto con:

```bash
./build.sh
```

El script `build.sh` compila el código Java y luego ejecuta la aplicación en una sola instrucción.

## Comandos disponibles

```bash
./build.sh        # compila y ejecuta la aplicación
./build.sh compile # solo compila el código
./build.sh test    # ejecuta las pruebas unitarias
./build.sh clean   # elimina archivos generados
```

## Por qué usar `build.sh`

`build.sh` simplifica el proceso de compilación y ejecución. Garantiza que todos los archivos fuente se compilen con los ajustes correctos y evita tener que escribir manualmente comandos `javac` y `java`.
