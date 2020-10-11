package tcpClient

import ClientHandler
import RequestDataEncoder
import ResponseDataDecoder
import io.netty.bootstrap.Bootstrap
import io.netty.channel.ChannelFuture
import io.netty.channel.ChannelInitializer
import io.netty.channel.ChannelOption
import io.netty.channel.EventLoopGroup
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioSocketChannel

object TcpClient {
    private var workerGroup: EventLoopGroup = NioEventLoopGroup()

    fun connect() {
        //try {

            val bootstrap = Bootstrap()
            bootstrap.group(workerGroup)
            bootstrap.channel(NioSocketChannel::class.java)
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true)
            bootstrap.handler(object : ChannelInitializer<SocketChannel>() {
                @Throws(Exception::class)
                override fun initChannel(ch: SocketChannel) {
                    ch.pipeline().addLast(
                        RequestDataEncoder(),
                        ResponseDataDecoder(), ClientHandler()
                    )
                }
            })
            val channelFuture: ChannelFuture = bootstrap.connect(Config.PREDHOST_IP, Config.PREDHOST_PORT).sync()
            channelFuture.channel().closeFuture().sync()
        //} //catch (e:Exception) {
            //val i=0



        //}

    } //finally
    //{
    //    workerGroup.shutdownGracefully()
    //}


}





