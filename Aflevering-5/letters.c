#include <stdio.h>

main(int argc, char *argv[]) {
    int i;
    int n = 0;
    for (i = 1; i < argc; ++i) {
        char *a = argv[i];
        while(*a != '\0') {
            if(((64 < *a) && (*a <= 90)) || ((96 < *a) && (*a <= 122))) {
                n++;
	        }
            a++;
        }
    }
    
    printf("%d", n);
}
