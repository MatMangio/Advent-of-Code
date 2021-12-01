#include <stdio.h>

int main(void) {
    int prev = -1, curr, count = 0, num = 1;
    while(num != 0) {
        num = scanf("%d", &curr);
        if (prev != -1 && curr > prev) count++;
        prev = curr;
    }
    printf("Count: %d\n", count);
    return 0;
}