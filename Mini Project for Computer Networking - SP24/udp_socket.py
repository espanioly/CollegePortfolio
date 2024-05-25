import socket
import random
import sys


def init_udp_socket() -> socket.socket:
    #raise NotImplemented("Please implement this function")
    s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    return s


def bind_local_port(s: socket.socket, addr: str, port: int) -> bool:
    #raise NotImplemented("Please implement this function")
    return True


def send_uuid(s: socket.socket, uuid: str):
    #raise NotImplemented("Please implement this function")
    s.sendto(uuid.encode(),("127.0.0.1",54321))

# def get_uuid(s: socket.socket) -> str:
#     #raise NotImplemented("Please implement this function")
#      = s.receive(4096)
#     return uuid

def load_uuid() -> str:
    #raise NotImplemented("Please implement this function")
    uid = "ca109ab4-0b4e-4713-b1bc-6bd5c2ecb7c6"
    return uid


def get_confirmation(s: socket.socket) -> str:
    #raise NotImplemented("Please implement this function")
    datarecv = s.recvfrom(4096)
    return datarecv


if __name__ == "__main__":
    uuid = load_uuid()
    s = init_udp_socket()
    bind_local_port(s, "0.0.0.0", random.randint(11024, 65535))
    send_uuid(s, uuid)
    confirmation = get_confirmation(s)
    print(confirmation)
    s.close()
