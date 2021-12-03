if __name__ == "__main__":
    oxy = []
    while (True):
        binStr = input()
        if (len(binStr) < 1): break
        oxy.append(binStr)
    
    co2 = oxy
    i = 0
    while (len(oxy) > 1):
        #Calculate the most common value
        ones = 0
        zeros = 0
        for s in oxy:
            if (s[i] == '1'): ones = ones + 1
            else: zeros = zeros + 1
        if (ones >= zeros):
            most = '1'
        else:
            most = '0'
        
        #Take out values
        for s in oxy:
            if (s[i] != most):
                oxy = [value for value in oxy if value != s]
        i = i + 1

    i = 0
    while (len(co2) > 1):
        ones = 0
        zeros = 0
        for s in co2:
            if (s[i] == '1'): ones = ones + 1
            else: zeros = zeros + 1
        if (ones >= zeros):
            least = '0'
        else:
            least = '1'

        for s in co2:
            if (s[i] != least):
                co2 = [value for value in co2 if value != s]
        i = i + 1
    
    print(oxy, "->", int(oxy[0], 2))
    print(co2, "->", int(co2[0], 2));
    print("Life support:", int(oxy[0], 2) * int(co2[0], 2))