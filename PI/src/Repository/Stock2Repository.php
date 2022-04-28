<?php

namespace App\Repository;

use App\Entity\Stock2;
use App\Entity\Partenaires;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\ORM\OptimisticLockException;
use Doctrine\ORM\ORMException;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Stock2|null find($id, $lockMode = null, $lockVersion = null)
 * @method Stock2|null findOneBy(array $criteria, array $orderBy = null)
 * @method Stock2[]    findAll()
 * @method Stock2[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class Stock2Repository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Stock2::class);
    }

    /**
     * @throws ORMException
     * @throws OptimisticLockException
     */
    public function add(Stock2 $entity, bool $flush = true): void
    {
        $this->_em->persist($entity);
        if ($flush) {
            $this->_em->flush();
        }
    }

    /**
     * @throws ORMException
     * @throws OptimisticLockException
     */
    public function remove(Stock2 $entity, bool $flush = true): void
    {
        $this->_em->remove($entity);
        if ($flush) {
            $this->_em->flush();
        }
    }



    // /**
    //  * @return Stock2[] Returns an array of Stock2 objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('s')
            ->andWhere('s.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('s.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Stock2
    {
        return $this->createQueryBuilder('s')
            ->andWhere('s.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
################################################ STAT FRONT ###########################################################################"" 

   public function selectNomMarque()
   {
    return $this->getEntityManager()
    ->createQuery(
        'SELECT s.qualiteS as qualite ,p.nomMarqueP as nomM
        FROM App\Entity\Stock2 s ,App\Entity\Partenaires p
        WHERE s.partenaire =p.id'
    )
    
    ->getResult();
   }

################################################ STAT BACK PIE CHART ###########################################################################"" 

   public function selectNomStockQte()
   {
    return $this->getEntityManager()
    ->createQuery(
        'SELECT s.qteS as quantite ,s.nomS as nomStock
        FROM App\Entity\Stock2 s'
        
    )
    
    ->getResult();
   }



################################################ MAIL PARTENAIRE QTE<10 ###########################################################################"" 

public function selectMailQte()
{
    return $this->getEntityManager()
    ->createQuery(
        'SELECT p.mailP as mail 
        FROM App\Entity\Stock2 s ,App\Entity\Partenaires p
        WHERE s.partenaire =p.id
        and s.qteS <10'
    )
    
    ->getResult();
     
 
}

 ################################################TRIE STOCK PAR QTE DESC ###########################################################################"" 


   public function TRIE()
   {
    return $this->createQueryBuilder('s')
    ->addOrderBy('s.qteS', 'DESC')
    ->getQuery()
    ->getResult()
;
        

    }



}