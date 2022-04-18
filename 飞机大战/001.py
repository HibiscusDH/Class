import pygame
from pygame import *
import time


def main():
    screen = pygame.display.set_mode((480, 853), 0, 32)
    x = 480 / 2 - 100 / 2
    y = 600
    spend = 5
    while True:
        """背景图"""
        background = pygame.image.load("./photo/background.png")
        screen.blit(background, (0, 0))
        """飞机1"""
        player = pygame.image.load("./photo/me1.png")
        screen.blit(player, (x, y))

        for event in pygame.event.get():
            """退出界面"""
            if event.type == QUIT:
                pygame.quit()
                exit()

        key_pressed = pygame.key.get_pressed()

        if key_pressed[K_w] or key_pressed[K_UP]:
            print("上")
            y -= spend
        if key_pressed[K_s] or key_pressed[K_DOWN]:
            print("下")
            y += spend
        if key_pressed[K_a] or key_pressed[K_LEFT]:
            print("左")
            x -= spend
        if key_pressed[K_d] or key_pressed[K_RIGHT]:
            print("右")
            x += spend
        if key_pressed[K_SPACE]:
            print("空格")

        pygame.display.update()
        time.sleep(0.01)


if __name__ == '__main__':
    main()