if __name__ == "__main__":
    count = 0
    total = []
    while (True):
        numStr = input()
        if (numStr[0] == '\x04'): break
        for i in range(len(numStr)):
            if i < len(total):
                total[i] = total[i] + int(numStr[i])
            else:
                total.append(int(numStr[i]))
        count = count + 1

    epsilon = ""
    gamma = ""
    for i in total:
        if ((float(i) / float(count)) > 0.5):
            epsilon = epsilon + "1"
            gamma = gamma + "0"
        else:
            epsilon = epsilon + "0"
            gamma = gamma + "1"
    
    print("Epsilon * Gamma:", int(epsilon, 2) * int(gamma, 2))
