INTEGRANTES:
Cristian Velosa

DESCRIPCION:
Sistema de gestion de prestamos para libros y revistas de una biblioteca usando Programacion Orientada a Objetos en Java sin IDE.

COMANDOS (Ejecutados desde la raiz del proyecto):

1. Compilar:
javac -encoding UTF-8 -d bin -sourcepath src src\co\edu\universidad\biblioteca\App.java

2. Ejecutar:
java -cp bin co.edu.universidad.biblioteca.App

3. Empaquetar:
jar cfe dist/biblioteca.jar co.edu.universidad.biblioteca.App -C bin .


RESPUESTAS A PREGUNTAS DE ANALISIS:

1. ¿Qué diferencia hay entre lo que contiene src y lo que contiene bin? ¿Qué pasa si borra bin y vuelve a compilar?
'src' contiene únicamente el código fuente legible (.java), mientras que 'bin' contiene el bytecode compilado (.class) que genera la máquina. Si se borra la carpeta 'bin', no se pierde nada crítico, ya que se puede regenerar automáticamente al volver a ejecutar el comando 'javac'.

2. En App las variables se declaran como Material m1 = new Libro(...). Cuando se llama m1.diasMaximoPrestamo(), ¿cómo sabe Java si responder 15 o 5? ¿Cómo se llama ese concepto?
Se llama Polimorfismo (y ligadura dinámica). Aunque la variable es de tipo 'Material', el objeto real construido en la memoria es un 'Libro', por lo que Java ejecuta en tiempo de ejecución el método sobrescrito específico de la clase 'Libro'.

3. ¿Por qué Material es abstracta? ¿Qué ganaría o perdería si fuera una clase normal?
Es abstracta porque en el dominio del problema no existe un "Material" genérico que se pueda prestar, solo Libros o Revistas. Si fuera una clase normal, se podrían instanciar objetos Material sin reglas claras de préstamo (como el límite de días), lo que llevaría a inconsistencias en el negocio.

4. Usuario.getPrestamos() devuelve una copia de la lista. ¿Qué problema de encapsulamiento se evitaría con eso?
Evita que código externo modifique los préstamos directamente. Si devolviera la lista original, se podría hacer algo como: usuario.getPrestamos().add(otroMaterial); rompiendo la regla R5 (límite de 3 préstamos) sin pasar por las validaciones de la clase Biblioteca.

5. ¿Por qué las reglas del negocio están en Biblioteca y no en App?
Porque 'App' es solo el punto de entrada (presentación) y no debe tener la lógica de la aplicación. Poner las reglas en 'Biblioteca' (servicio) aplica el principio de responsabilidad única, separando la interfaz de usuario de la lógica de negocio, lo cual es la base de arquitecturas como MVC.

6. ¿Por qué PrestamoException extiende de Exception y no de RuntimeException? ¿Qué cambiaría en el código de App?
Extiende de 'Exception' para que sea una excepción verificada (checked). Esto obliga en tiempo de compilación a que la clase 'App' deba manejar obligatoriamente el error con bloques try/catch. Si fuera 'RuntimeException', el compilador no obligaría a atraparla, y si ocurriera un error de préstamo, el programa se caería en lugar de mostrar el mensaje y continuar.