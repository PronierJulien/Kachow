#!/bin/bash

# Démarrage des services API en arrière-plan
java -jar /app/api-auth.jar & 
java -jar /app/api-invocations.jar & 
java -jar /app/api-joueur.jar & 
java -jar /app/api-monstres.jar & 

# Attente de la fin des processus
wait