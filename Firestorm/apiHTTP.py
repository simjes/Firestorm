from BaseHTTPServer import BaseHTTPRequestHandler, HTTPServer
import RPi.GPIO as gpio

gpio.setmode(gpio.BOARD)
gpio.setup(11, gpio.OUT, initial=gpio.LOW, pull_up_down=gpio.PUD_DOWN)
gpio.setup(16, gpio.OUT, initial=gpio.LOW, pull_up_down=gpio.PUD_DOWN)

PORT = 8000

class myHandler(BaseHTTPRequestHandler):
    def do_GET(self):
        if self.path=="/hot":
            gpio.output(11, gpio.HIGH)
        elif self.path=="/hot_stop":
            gpio.output(11, gpio.LOW)
        elif self.path=="/cold":
            gpio.output(16, gpio.HIGH)
        elif self.path=="/cold_stop":
            gpio.output(16, gpio.LOW)


try:
    server = HTTPServer(("" ,PORT), myHandler)
    print'server listens'
    server.serve_forever()
except KeyboardInterrupt:
    print'shutting down'
    gpio.cleanup()
    server.socket.close()

