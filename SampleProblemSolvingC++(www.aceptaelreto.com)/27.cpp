#include <iostream>
#include <vector>
using namespace std;

/*
El problema se resuelve de manera similar al problema de la mochila, pero en vez de distringuir si
se coge el objeto o no, se distingue entre si cogerlo en la primera cara del CD, en la segunda cara
o si no se coge.
*/

typedef struct {
	int duracion;
	int puntuacion;
	double rendimiento;
}tCancion;

void swap(vector<tCancion> & v, int c1, int c2) {
	tCancion temp = v[c1];
	v[c1] = v[c2];
	v[c2] = temp;
}

int divide(vector<tCancion> & v, int start, int end) {
	int left = start;
	int right = end;
	double pivot = v[start].rendimiento;
	// Mientras no se cruzen los índices
	while (left < right) {
		while (v[right].rendimiento < pivot)
			right--;
		while ((left < right) && (v[left].rendimiento >= pivot))
			left++;
		// Si todavía no se cruzan los indices seguimos intercambiando
		if (left < right)
			swap(v, left, right);
	}
	// Los índices ya se han cruzado, ponemos el pivot en el lugar que le corresponde
	swap(v, right, start);
	return right;							// La nueva posición del pivot
}

// Función recursiva para hacer el ordenamiento
void quicksort(vector<tCancion> & v, int start, int end) {
	if (start < end) {
		int pivot = divide(v, start, end);
		quicksort(v, start, pivot - 1);			// Ordeno la lista de los menores
		quicksort(v, pivot + 1, end);			// Ordeno la lista de los mayores
	}
}

double estimacion(vector<tCancion> & v, int N, int M, int cont, int peso1, int peso2, int valor) {
	double hueco = 2 * M - peso1 - peso2;
	double estimacion = valor;
	int j = cont + 1;
	while (j < N && v[j].duracion <= hueco) {
		hueco -= v[j].duracion;
		estimacion += v[j].puntuacion;
		j++;
	}
	if (j < N)
		estimacion += v[j].rendimiento * hueco;
	return estimacion;
}

void resolver(vector<tCancion> & v, int N, int M, int cont, int peso1, int peso2, int valor, int & sol) {
	// si cogemos el objeto en la primera cara
	if (v[cont].duracion + peso1 <= M) {
		valor += v[cont].puntuacion;
		if (cont == N - 1) {
			if (valor > sol)
				sol = valor;
		}
		else
			resolver(v, N, M, cont + 1, peso1 + v[cont].duracion, peso2, valor, sol);
		valor -= v[cont].puntuacion;
	}
	// si cogemos el objeto en la segunda cara
	if (v[cont].duracion + peso2 <= M) {
		valor += v[cont].puntuacion;
		if (cont == N - 1) {
			if (valor > sol)
				sol = valor;
		}
		else
			resolver(v, N, M, cont + 1, peso1, peso2 + v[cont].duracion, valor, sol);
		valor -= v[cont].puntuacion;
	}
	// si no cogemos el objeto
	if (cont == N - 1) {
		if (valor > sol)
			sol = valor;
	}
	else {
		if(estimacion(v, N, M, cont, peso1, peso2, valor) > sol)		// estimacion
			resolver(v, N, M, cont + 1, peso1, peso2, valor, sol);
	}
}

bool resuelveCaso() {
	int N, M;
	cin >> N;
	if (N == 0)
		return false;
	cin >> M;
	vector<tCancion> v(N);
	for (int i = 0; i < N; i++) {
		cin >> v[i].duracion >> v[i].puntuacion;
		v[i].rendimiento = (v[i].puntuacion + 0.0) / v[i].duracion;
	}
	quicksort(v, 0, N - 1);				// ordenamos por rendimiento
	int sol = 0;
	resolver(v, N, M, 0, 0, 0, 0, sol);
	cout << sol << endl;
	return true;
}

int main() {
	while (resuelveCaso());
	return 0;
}