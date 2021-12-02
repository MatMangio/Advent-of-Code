#include <stdio.h>

int main(void) {
    int count = 0, n1 = 0, n2 = 0, n3 = 0, n4 = 0;

    for (int i = 0, num = -1;; i++) {
        n4 = n3;
        n3 = n2;
        n2 = n1;
        num = scanf("%d", &n1);
        if (num == 0) break;
        if (n1 > n4 && i >= 3) count++;
    }

    printf("Risultato: %d\n", count);
    return 0;
}