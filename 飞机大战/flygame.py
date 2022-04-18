import pygame
from pygame import *
import time


class HeroPlane(object):
    def __init__(self, screen):
        """飞机1"""
        self.player = pygame.image.load("./photo/me1.png")
        self.x = 480 / 2 - 100 / 2
        self.y = 600

        """飞机速度"""
        self.spend = 5
        """记录当前的窗口"""
        self.screen = screen
        """装子弹的列表"""
        self.bullets = []

    def key_control(self):
        key_pressed = pygame.key.get_pressed()

        if key_pressed[K_w] or key_pressed[K_UP]:
            self.y -= self.spend
        if key_pressed[K_s] or key_pressed[K_DOWN]:
            self.y += self.spend
        if key_pressed[K_a] or key_pressed[K_LEFT]:
            self.x -= self.spend
        if key_pressed[K_d] or key_pressed[K_RIGHT]:
            self.x += self.spend
        if key_pressed[K_SPACE]:
            bullet = Bullet(self.screen, self.x, self.y)
            self.bullets.append(bullet)

    def display(self):
        self.screen.blit(self.player, (self.x, self.y))
        for bullet in self.bullets:
            """子弹的y坐标"""
            bullet.auto_move()
            """子弹在窗口显示"""
            bullet.display()


class EnemyPlane(object):
    def __init__(self, screen):
        """飞机1"""
        self.player = pygame.image.load("./photo/enemy1.png")
        self.x = 0
        self.y = 0

        """飞机速度"""
        self.spend = 15
        """记录当前的窗口"""
        self.screen = screen
        """装子弹的列表"""
        self.bullets = []
        """敌机移动方向"""
        self.direction = 'right'

    def display(self):
        self.screen.blit(self.player, (self.x, self.y))
        # for bullet in self.bullets:
        #     """子弹的y坐标"""
        #     bullet.auto_move()
        #     """子弹在窗口显示"""
        #     bullet.display()

    def auto_move(self):
        if self.direction == 'right':
            self.x += self.spend
        elif self.direction == 'left':
            self.x -= self.spend


class Bullet(object):
    def __init__(self, screen, x, y):
        self.x = x+100/2-22/2
        self.y = y-22
        self.image = pygame.image.load("./photo/bullet1.png")
        self.screen = screen
        self.speed = 10

    def display(self):
        """将飞机贴到窗口上"""
        self.screen.blit(self.image, (self.x, self.y))

    def auto_move(self):
        """让子弹飞"""
        self.y -= self.speed


def main():
    """创建一个窗口"""
    screen = pygame.display.set_mode((480, 853), 0, 32)
    """背景图"""
    background = pygame.image.load("./photo/background.png")
    """飞机"""
    player = HeroPlane(screen)
    """敌方飞机"""
    enemyplan = EnemyPlane(screen)
    while True:
        """将背景图贴到窗口上"""
        screen.blit(background, (0, 0))
        """飞机1"""

        """遍历所有事件"""
        for event in pygame.event.get():
            """退出界面"""
            if event.type == QUIT:
                pygame.quit()
                exit()
        """执行按键监听"""
        player.key_control()
        """显示飞机"""
        player.display()
        """显示敌机"""
        enemyplan.display()
        """敌机自动移动"""
        enemyplan.auto_move()
        """更新需要显示的内容"""
        pygame.display.update()
        time.sleep(0.01)


if __name__ == '__main__':
    main()