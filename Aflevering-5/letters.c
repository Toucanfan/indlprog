#include <stdio.h>
#include <string.h>

main() {
    char *a = "a b c";
    int n = 0;
    while(*a != '\0') {
        if((32 < *a) && (*a <= 126)) {
            n++;
        }
        a++;
    }
    
    printf("%d", n);
}
