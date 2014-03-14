// goPiHttp
package main

import (
	"fmt"
	"log"
	"net/http"
	"os"
	"os/signal"

	"github.com/mrmorphic/hwio"
)

var pinHot, _ = hwio.GetPin("gpio4")
var pinCold, _ = hwio.GetPin("gpio22")

func main() {
	e := hwio.PinMode(pinHot, hwio.OUTPUT)
	if e != nil {
	  fmt.Print("fail paa 4	")	  
	  panic(e)

	}

	er := hwio.PinMode(pinCold, hwio.OUTPUT)
	if er != nil {
	  fmt.Print("feil paa 22  ")
	  panic(e)
	}

	http.HandleFunc("/hot", hotOn)
	http.HandleFunc("/hot_stop", hotOff)
	http.HandleFunc("/cold", coldOn)
	http.HandleFunc("/cold_stop", coldOff)
	if err := http.ListenAndServe(":8000", nil); err != nil {
		log.Fatal("failed to start server", err)
	}

	
	//no cleanup ?
	//manual cleanup in terminal: echo "4" > /sys/class/gpio/unexport
	//manual cleanup in terminal: echo "22" > /sys/class/gpio/unexport
	c := make(chan os.Signal, 1)
	signal.Notify(c, os.Interrupt)
	go func() {
		for _ = range c {
			fmt.Printf("\nClearing pins etc. \n")
			hwio.DigitalWrite(pinHot, hwio.LOW)
			hwio.DigitalWrite(pinCold, hwio.LOW)
			os.Exit(0)
		}
	}()
}

func hotOn(writer http.ResponseWriter, request *http.Request) {
	fmt.Print("Hot\n")
	hwio.DigitalWrite(pinHot, hwio.HIGH)
}

func hotOff(writer http.ResponseWriter, request *http.Request) {
	fmt.Print("Hot Stop\n")
	hwio.DigitalWrite(pinHot, hwio.LOW)
}

func coldOn(writer http.ResponseWriter, request *http.Request) {
	fmt.Print("Cold\n")
	hwio.DigitalWrite(pinCold, hwio.HIGH)
}

func coldOff(writer http.ResponseWriter, request *http.Request) {
	fmt.Print("Cold stop\n")
	hwio.DigitalWrite(pinCold, hwio.LOW)
}
