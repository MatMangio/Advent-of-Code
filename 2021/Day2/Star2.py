if __name__ == "__main__":
    x = 0 
    y = 0
    aim = 0
    while (True):
        s = input()
        parsedInput = s.split(" ")

        if (len(parsedInput) < 2): break

        if (parsedInput[0] == "forward"):
            x += int(parsedInput[1])
            y += aim * int(parsedInput[1])
        elif (parsedInput[0] == "down"):
            aim += int(parsedInput[1])
        else:
            aim -= int(parsedInput[1])
    print("\nRisultato:", x * y);