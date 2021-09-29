import csv

f = open("prueba.csv")
lns = csv.reader(f, delimiter=";")
for line in lns:
    nom = line[0]
    ape = line[1]
    ed = line[2]
    print("Nom: " + nom + "\t Ape: " + ape + "\t Edad: " + ed)
f.close()
