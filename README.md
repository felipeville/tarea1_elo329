# Tarea 1 ELO329

## Etapa 1

Consola:

```
javac Stage1Test.java
java Stage1Test entrada.csv > salida.csv
```

## Etapa 2

El archivo de entrada se supone que hace lo siguiente:
```
1. Deja elevar el drone hasta 1 [m]
2. Sube y baja al drone (en ese orden)
3. Rota hasta (pi/2 + pi/4) [rad]
4. Se mueve hacia delante y al lado, y luego retorna al origen
5. Rota nuevamente hasta 0 [rad] (aproximadamente)
6. Se mueve hacia delante y hacia el lado
7. Aterriza
```

## Etapa 3

Después del archivo de entrada, el drone queda con dirección 'forward' en un ángulo de 0°, es decir, hacia las coordenadas 'x' positivas.

## Etapa 4

Se crean 2 instancias de Drones junto con sus controladores, uno controlado por el archivo entrada.csv (dibuja las letras USM) y el otro controlado por teclado. El programa finaliza cuando ambos drones están aterrizados.
