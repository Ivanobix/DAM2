class Instrumento:
    precio = 0;

    def __init__(self, pre):
        self.precio = pre;


class Guitarra(Instrumento):  # Podria heredar de más de una clase (Herencia Múltiple)
    n_cuerdas = 0;

    def __init__(self, n, prec):
        super().__init__(prec);
        self.n_cuerdas = n;


miGuitarra = Guitarra(5, 10);
